package de.mhens.events.eventconsumer.integration.processinstance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import de.mhens.events.eventconsumer.elastic.processinstance.HistoricProcessInstance;
import de.mhens.events.eventconsumer.elastic.processinstance.HistoricProcessInstanceRepository;
import de.mhens.events.eventconsumer.integration.ProcessEngineEventChannels;

@Transactional
@Component
public class HistoricProcessInstanceServiceActivator {

	private final String HANDABLE_EVENT_TYPE="de.mhens.events.eventconsumer.elastic.HistoricProcessInstance";
	
	@Autowired
	private HistoricProcessInstanceRepository repository;
	
	@StreamListener(value = ProcessEngineEventChannels.ELASTIC_IN, condition = "headers['elasticType']=='"+HANDABLE_EVENT_TYPE+"'")	
	public void handle(HistoricProcessInstance message) {
		repository.save(message);
	}
}
