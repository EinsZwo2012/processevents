package de.mhens.events.integration.transformers;

import org.camunda.bpm.engine.impl.history.event.HistoricActivityInstanceEventEntity;
import org.springframework.stereotype.Component;

import de.mhens.events.dto.HistoricActivityInstanceDto;

@Component
public class HistoricActivityInstanceEventTransformer implements HistoryEventTransformer<HistoricActivityInstanceEventEntity, HistoricActivityInstanceDto>{

	@Override
	public HistoricActivityInstanceDto transform(HistoricActivityInstanceEventEntity event) {
		
		HistoricActivityInstanceDto dto = new HistoricActivityInstanceDto(
				event.getId(), 
				event.getProcessInstanceId(), 
				event.getStartTime(), 
				event.getEndTime(), 
				event.getActivityId(), 
				event.getActivityName(), 
				event.getActivityType(), 
				event.getActivityInstanceId(), 
				event.getActivityInstanceState());	
		
		return dto;
	}

	@Override
	public boolean isSuitable(String eventName) {
		return HistoricActivityInstanceEventEntity.class.getName().equals(eventName);
	}

}
