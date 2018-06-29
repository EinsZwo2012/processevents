package de.seriea.nx3.prototype.eventconsumer;

import org.camunda.bpm.engine.impl.history.event.HistoricProcessInstanceEventEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
public class EventListener {

	@Autowired
	private HistoricProcessInstanceService service;
	
	@StreamListener(target = ProcessEngineEventChannels.PROCESS_INSTANCES_IN
			, condition = "headers['historyEventType']=='org.camunda.bpm.engine.impl.history.event.HistoricProcessInstanceEventEntity'")
	public void handle(HistoricProcessInstanceEventEntity event) {
		HistoricProcessInstance instance = service.createFrom(event);
		service.save(instance);
	}
}
