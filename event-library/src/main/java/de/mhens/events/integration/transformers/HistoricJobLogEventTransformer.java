package de.mhens.events.integration.transformers;

import org.camunda.bpm.engine.impl.persistence.entity.HistoricJobLogEventEntity;
import org.springframework.stereotype.Component;

import de.mhens.events.dto.HistoricJobInstanceDto;

@Component
public class HistoricJobLogEventTransformer implements HistoryEventTransformer<HistoricJobLogEventEntity, HistoricJobInstanceDto>{

	@Override
	public HistoricJobInstanceDto transform(HistoricJobLogEventEntity event) {
		
		HistoricJobInstanceDto dto = new HistoricJobInstanceDto(
				event.getId(), 
				event.getActivityId(), 
				event.getState(), 
				event.getProcessInstanceId(), 
				event.getJobDueDate());
		
		return dto;
	}

	@Override
	public boolean isSuitable(String eventName) {
		return HistoricJobLogEventEntity.class.getName().equals(eventName);
	}

}
