package de.mhens.events.eventproducer.integration.transformers;

import org.camunda.bpm.engine.impl.history.event.HistoricProcessInstanceEventEntity;
import org.springframework.stereotype.Component;

import de.mhens.events.dto.HistoricProcessInstanceDto;

@Component
public class HistoricProcessInstanceEventTransformer implements HistoryEventTransformer<HistoricProcessInstanceEventEntity, HistoricProcessInstanceDto>{

	@Override
	public HistoricProcessInstanceDto transform(HistoricProcessInstanceEventEntity event) {
		
		HistoricProcessInstanceDto dto = new HistoricProcessInstanceDto(
				event.getId(), 
				event.getProcessInstanceId(), 
				event.getBusinessKey(), 
				event.getStartActivityId(), 
				event.getEndActivityId(), 
				event.getDeleteReason(), 
				event.getStartTime(), 
				event.getEndTime())	;	
		
		return dto;
	}

	@Override
	public boolean isSuitable(String eventName) {
		return HistoricProcessInstanceEventEntity.class.getName().equals(eventName);
	}

}
