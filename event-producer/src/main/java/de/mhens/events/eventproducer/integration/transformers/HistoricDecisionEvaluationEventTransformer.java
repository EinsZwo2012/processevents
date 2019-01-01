package de.mhens.events.eventproducer.integration.transformers;

import org.camunda.bpm.engine.impl.history.event.HistoricDecisionEvaluationEvent;
import org.camunda.bpm.engine.impl.history.event.HistoricDecisionInstanceEntity;
import org.springframework.stereotype.Component;

import de.mhens.events.dto.HistoricDecisionInstanceDto;

@Component
public class HistoricDecisionEvaluationEventTransformer implements HistoryEventTransformer<HistoricDecisionEvaluationEvent, HistoricDecisionInstanceDto>{

	@Override
	public HistoricDecisionInstanceDto transform(HistoricDecisionEvaluationEvent event) {
		
		HistoricDecisionInstanceEntity root = event.getRootHistoricDecisionInstance();
		
		HistoricDecisionInstanceDto dto = new HistoricDecisionInstanceDto(
				root.getId(), 
				root.getDecisionDefinitionId(), 
				root.getDecisionDefinitionKey(), 
				root.getDecisionDefinitionName(), 
				root.getActivityInstanceId(), 
				root.getActivityId(), 
				root.getRootDecisionInstanceId(), 
				root.getDecisionRequirementsDefinitionId(), 
				root.getDecisionRequirementsDefinitionKey(), 
				root.getEvaluationTime());
		
		return dto;
	}

	@Override
	public boolean isSuitable(String eventName) {
		return HistoricDecisionEvaluationEvent.class.getName().equals(eventName);
	}

}
