package de.mhens.events.integration.transformers;

import org.camunda.bpm.engine.impl.history.event.HistoricTaskInstanceEventEntity;
import org.springframework.stereotype.Component;

import de.mhens.events.dto.HistoricTaskInstanceDto;

@Component
public class HistoricTaskInstanceEventTransformer implements HistoryEventTransformer<HistoricTaskInstanceEventEntity, HistoricTaskInstanceDto>{

	@Override
	public HistoricTaskInstanceDto transform(HistoricTaskInstanceEventEntity event) {
		
		HistoricTaskInstanceDto dto = new HistoricTaskInstanceDto(
				event.getId(), 
				event.getProcessInstanceId(), 
				event.getStartTime(), 
				event.getEndTime(), 
				event.getTaskId(),
				event.getTaskDefinitionKey());
		return dto;
	}

	@Override
	public boolean isSuitable(String eventName) {
		return HistoricTaskInstanceEventEntity.class.getName().equals(eventName);
	}

}
