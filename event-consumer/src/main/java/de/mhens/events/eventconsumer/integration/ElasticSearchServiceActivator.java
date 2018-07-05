package de.mhens.events.eventconsumer.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import de.mhens.events.eventconsumer.elastic.HistoricProcessInstance;
import de.mhens.events.eventconsumer.elastic.HistoricProcessInstanceService;

@Transactional
@Component
public class ElasticSearchServiceActivator {

	@Autowired
	private HistoricProcessInstanceService service;
	
	@ServiceActivator(inputChannel = ProcessEngineEventChannels.ELASTIC_IN)
	public void handle(HistoricProcessInstance message) {
		//throw new RuntimeException("EX");
		service.save(message);
	}
	
	@ServiceActivator(inputChannel = ProcessEngineEventChannels.ELASTIC_ERRORS)
	public void error(Message<?> message) {	
		System.out.println("Handling ERROR: " + message);
	}
}
