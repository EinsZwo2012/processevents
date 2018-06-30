package de.mhens.events.eventconsumer.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Component;

import de.mhens.events.eventconsumer.elastic.HistoricProcessInstance;
import de.mhens.events.eventconsumer.elastic.HistoricProcessInstanceService;

@Component
public class ElasticSearchServiceActivator {

	@Autowired
	private HistoricProcessInstanceService service;
	
	@ServiceActivator(inputChannel = ProcessEngineEventChannels.ELASTIC)
	public void handle(HistoricProcessInstance message) {
		service.save(message);
	}
}
