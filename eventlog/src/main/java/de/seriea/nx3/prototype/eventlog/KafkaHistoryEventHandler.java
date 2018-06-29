package de.seriea.nx3.prototype.eventlog;

import java.util.List;

import org.camunda.bpm.engine.impl.history.event.HistoricProcessInstanceEventEntity;
import org.camunda.bpm.engine.impl.history.event.HistoryEvent;
import org.camunda.bpm.engine.impl.history.handler.HistoryEventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Transactional
public class KafkaHistoryEventHandler implements HistoryEventHandler {

	@Autowired
	private ProcessEngineEventChannels channels;
	
	@HystrixCommand
	@Override
	public void handleEvent(HistoryEvent historyEvent) {
		
		if(historyEvent instanceof HistoricProcessInstanceEventEntity) {
			channels.processEvents().send(MessageBuilder
									.withPayload(historyEvent)
									.setHeader("partitionKey", historyEvent.getId())
									.setHeader("historyEventType", historyEvent.getClass().getName())
									.build(), 2000l);
		}else {
			log.info("Not handled");
		}
	}

	@Override
	public void handleEvents(List<HistoryEvent> historyEvents) {
		historyEvents.forEach(event -> handleEvent(event));
	}

}
