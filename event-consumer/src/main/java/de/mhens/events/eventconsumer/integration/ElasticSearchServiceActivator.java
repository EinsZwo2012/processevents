package de.mhens.events.eventconsumer.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

import de.mhens.events.eventconsumer.elastic.HistoricProcessInstance;
import de.mhens.events.eventconsumer.elastic.HistoricProcessInstanceService;

@Component
public class ElasticSearchServiceActivator {

	@Autowired
	private HistoricProcessInstanceService service;
	
	@StreamListener(target = ProcessEngineEventChannels.ELASTIC)
	public void handle(HistoricProcessInstance message) {
		service.save(message);
	}
}
