package de.mhens.events.eventconsumer.integration;

import org.camunda.bpm.engine.impl.history.event.HistoricProcessInstanceEventEntity;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import de.mhens.events.eventconsumer.elastic.HistoricProcessInstance;

@Transactional
@Component
public class ToElasticTransformer {
	
	@Transformer(inputChannel = ProcessEngineEventChannels.PROCESS_EVENTS, outputChannel = ProcessEngineEventChannels.ELASTIC_OUT)
	public HistoricProcessInstance transform(HistoricProcessInstanceEventEntity message) {
		//TODO: Something like a strategy pattern or factory for transforming of different types. Actual we only get HistoricProcessInstanceEventEntity messages
		HistoricProcessInstance instance = new HistoricProcessInstance(
				message.getId(), 
				message.getProcessInstanceId(),
				message.getStartTime(),
				message.getEndTime());

		return instance;
	}
	
	@ServiceActivator(inputChannel = ProcessEngineEventChannels.PROCESS_EVENT_ERRORS)
	public void error(Message<?> message) {
		System.out.println("Handling ERROR: " + message);
	}
}
