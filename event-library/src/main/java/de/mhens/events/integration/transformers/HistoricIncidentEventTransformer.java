package de.mhens.events.integration.transformers;

import org.camunda.bpm.engine.impl.history.event.HistoricIncidentEventEntity;
import org.springframework.stereotype.Component;

import de.mhens.events.dto.HistoricIncidentInstanceDto;

@Component
public class HistoricIncidentEventTransformer implements HistoryEventTransformer<HistoricIncidentEventEntity, HistoricIncidentInstanceDto>{

	@Override
	public HistoricIncidentInstanceDto transform(HistoricIncidentEventEntity event) {
		
		Integer incidentState = 0;
		if(event.isDeleted()) {
			incidentState =2;
		}else if(event.isOpen()) {
			incidentState =0;
		}else if (event.isResolved()) {
			incidentState = 1;
		}
		HistoricIncidentInstanceDto dto = new HistoricIncidentInstanceDto(
				event.getId(), 
				event.getCreateTime(), 
				event.getEndTime(), 
				event.getIncidentType(), 
				event.getActivityId(), 
				event.getCauseIncidentId(), 
				event.getRootCauseIncidentId(), 
				event.getConfiguration(), 
				event.getIncidentMessage(), 
				incidentState,
				event.getJobDefinitionId(),
				event.getProcessInstanceId());
		
		return dto;
	}

	@Override
	public boolean isSuitable(String eventName) {
		return HistoricIncidentEventEntity.class.getName().equals(eventName);
	}

}
