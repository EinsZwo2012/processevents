package de.seriea.nx3.eventconsumer.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

import de.seriea.nx3.eventconsumer.elastic.HistoricProcessInstance;
import de.seriea.nx3.eventconsumer.elastic.HistoricProcessInstanceService;

@Component
public class ElasticSearchServiceActivator {

	@Autowired
	private HistoricProcessInstanceService service;
	
	@StreamListener(target = ProcessEngineEventChannels.ELASTIC)
	public void handle(HistoricProcessInstance message) {
		service.save(message);
	}
}
