package de.mhens.events.eventconsumer.integration;

import org.camunda.bpm.engine.impl.history.event.HistoricProcessInstanceEventEntity;
import org.springframework.integration.annotation.Transformer;
import org.springframework.stereotype.Component;

import de.mhens.events.eventconsumer.elastic.HistoricProcessInstance;

@Component
public class ToElasticTransformer {
	
	@Transformer(inputChannel = ProcessEngineEventChannels.PROCESS_EVENTS, outputChannel = ProcessEngineEventChannels.ELASTIC)
	public Object transform(HistoricProcessInstanceEventEntity message) {
		
		//TODO: Something like a strategy pattern or factor for transforming of different types. Actual we only get HistoricProcessInstanceEventEntity messages
		
		HistoricProcessInstance instance = new HistoricProcessInstance(
				message.getId(), 
				message.getProcessInstanceId(),
				message.getStartTime(),
				message.getEndTime());

		return instance;
	}
}
