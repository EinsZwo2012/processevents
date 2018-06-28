package de.seriea.nx3.prototype.eventconsumer;

import org.camunda.bpm.engine.impl.history.event.HistoricActivityInstanceEventEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
public class EventListener {

	@Autowired
	private HistoricActivityInstanceServiceImpl service;
	
	@StreamListener(target = ProcessEngineEventChannels.ACTIVITIES_IN
			, condition = "headers['historyEventType']=='org.camunda.bpm.engine.impl.history.event.HistoricActivityInstanceEventEntity'")
	public void handle(HistoricActivityInstanceEventEntity event) {
		HistoricActivityInstance instance = service.createFrom(event);
		service.save(instance);
	}
}
