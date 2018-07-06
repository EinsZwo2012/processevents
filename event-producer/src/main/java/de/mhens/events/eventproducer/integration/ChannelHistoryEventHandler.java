package de.mhens.events.eventproducer.integration;

import java.util.ArrayList;
import java.util.List;

import org.camunda.bpm.engine.impl.history.event.HistoricProcessInstanceEventEntity;
import org.camunda.bpm.engine.impl.history.event.HistoryEvent;
import org.camunda.bpm.engine.impl.history.handler.HistoryEventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import lombok.extern.slf4j.Slf4j;

/**
 * Send {@link HistoryEvent}s to {@link ProcessEngineEventsChannel}.
 * This is done in concrete business transaction. If send to channel fails complete business trasaction will be rolled back.
 * @author markushens
 *
 */
@Slf4j
@Component
@Transactional
public class ChannelHistoryEventHandler implements HistoryEventHandler {

	@Autowired
	private ProcessEngineEventsChannel channels;
	
	private ArrayList<String> handledHistoryEvents;
	
	public ChannelHistoryEventHandler() {
		handledHistoryEvents = new ArrayList<>();
		
		handledHistoryEvents.add(HistoricProcessInstanceEventEntity.class.getName());
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
		Message<HistoryEvent> message = buildMessage(historyEvent);
		channels.processEvents().send(message);
	}

	private Message<HistoryEvent> buildMessage(HistoryEvent historyEvent) {
		return MessageBuilder
					.withPayload(historyEvent)
					.setHeader("partitionKey", historyEvent.getProcessInstanceId())
					.setHeader("historyEventType", historyEvent.getClass().getName())
					.build();
	}
}
