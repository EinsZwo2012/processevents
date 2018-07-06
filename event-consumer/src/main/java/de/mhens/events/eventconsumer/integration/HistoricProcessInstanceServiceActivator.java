package de.mhens.events.eventconsumer.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import de.mhens.events.eventconsumer.elastic.HistoricProcessInstance;
import de.mhens.events.eventconsumer.elastic.HistoricProcessInstanceService;

@Transactional
@Component
public class HistoricProcessInstanceServiceActivator {

	private final String HANDABLE_EVENT_TYPE="de.mhens.events.eventconsumer.elastic.HistoricProcessInstance";
	
	@Autowired
	private HistoricProcessInstanceService service;
	
	@StreamListener(value = ProcessEngineEventChannels.ELASTIC_IN, condition = "headers['elasticType']=='"+HANDABLE_EVENT_TYPE+"'")	
	public void handle(HistoricProcessInstance message) {
		service.save(message);
	}
	
	@ServiceActivator(inputChannel = ProcessEngineEventChannels.ELASTIC_ERRORS)
	public void error(Message<?> message) {	
		System.out.println("Handling ERROR: " + message);
	}
}
