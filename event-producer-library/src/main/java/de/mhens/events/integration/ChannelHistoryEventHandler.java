package de.mhens.events.integration;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.camunda.bpm.engine.impl.history.event.HistoricActivityInstanceEventEntity;
import org.camunda.bpm.engine.impl.history.event.HistoricDecisionEvaluationEvent;
import org.camunda.bpm.engine.impl.history.event.HistoricIncidentEventEntity;
import org.camunda.bpm.engine.impl.history.event.HistoricProcessInstanceEventEntity;
import org.camunda.bpm.engine.impl.history.event.HistoricTaskInstanceEventEntity;
import org.camunda.bpm.engine.impl.history.event.HistoryEvent;
import org.camunda.bpm.engine.impl.history.handler.HistoryEventHandler;
import org.camunda.bpm.engine.impl.persistence.entity.HistoricJobLogEventEntity;
import org.springframework.context.ApplicationContext;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.transaction.annotation.Transactional;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import de.mhens.events.dto.EventDto;
import de.mhens.events.integration.transformers.HistoryEventTransformer;
import lombok.extern.slf4j.Slf4j;

/**
 * Send {@link HistoryEvent}s to {@link ProcessEngineEventsChannel}.
 * This is done in concrete business transaction. If send to channel fails complete business transaction will be rolled back.
 * @author markushens
 *
 */
@Slf4j
@Transactional
public class ChannelHistoryEventHandler implements HistoryEventHandler {

	private ProcessEngineEventsChannel channels;
	
	private ApplicationContext ctx;
	
	//TODO ; Remove
	private ArrayList<String> handledHistoryEvents;
	
	private List<HistoryEventTransformer<HistoryEvent, EventDto>> transformers;
		
	
	public ChannelHistoryEventHandler(ProcessEngineEventsChannel channels,
									  ApplicationContext ctx) {
		this.channels=channels;
		this.ctx = ctx;
	}
	
	@PostConstruct
	public void init() {
		handledHistoryEvents = new ArrayList<>();
		
		handledHistoryEvents.add(HistoricProcessInstanceEventEntity.class.getName());	
		handledHistoryEvents.add(HistoricTaskInstanceEventEntity.class.getName());
		handledHistoryEvents.add(HistoricActivityInstanceEventEntity.class.getName());
		handledHistoryEvents.add(HistoricJobLogEventEntity.class.getName());
		handledHistoryEvents.add(HistoricIncidentEventEntity.class.getName());
		handledHistoryEvents.add(HistoricDecisionEvaluationEvent.class.getName());
		
		transformers = ctx.getBeansOfType(HistoryEventTransformer.class).values().stream().collect(Collectors.toList());
	}
	
	@Override
	public void handleEvents(List<HistoryEvent> historyEvents) {
		historyEvents.forEach(event -> handleEvent(event));
	}
	
	@HystrixCommand
	@Override
	public void handleEvent(HistoryEvent historyEvent) {
		
		if(isHandled(historyEvent)) {
			sendToChannel(historyEvent);
		}else {
			log.info("HistoryEvent of type {} in process instance {} is not handled", 
					historyEvent.getClass().getName(), 
					historyEvent.getProcessInstanceId());
		}
	}

	private boolean isHandled(HistoryEvent historyEvent) {
		return handledHistoryEvents.contains(historyEvent.getClass().getName());
	}

	private void sendToChannel(HistoryEvent historyEvent) {
		Message<EventDto> message = buildMessage(historyEvent);
		channels.processEvents().send(message);		
	}

	private Message<EventDto> buildMessage(final HistoryEvent historyEvent) {
		
		HistoryEventTransformer<HistoryEvent, EventDto> transformer = transformers.stream()
					.filter(t -> t.isSuitable(historyEvent.getClass().getName()))
					.findFirst()
					.orElseThrow(() ->new IllegalArgumentException("No transformer found"));
		
		
		EventDto dto = transformer.transform(historyEvent);
	
		return MessageBuilder
					.withPayload(dto)
					.setHeader("partitionKey", dto.getProcessInstanceId())
					.setHeader("historyEventType", historyEvent.getClass().getSimpleName())
					.build();
	}
}
