package de.mhens.events.eventconsumer.integration.taskinstance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import de.mhens.events.eventconsumer.elastic.activityinstance.HistoricActivityInstance;
import de.mhens.events.eventconsumer.elastic.activityinstance.HistoricActivityInstanceRepository;
import de.mhens.events.eventconsumer.integration.ProcessEngineEventChannels;

@Transactional
@Component
public class HistoricTaskInstanceServiceActivator {

	private final String HANDABLE_EVENT_TYPE="de.mhens.events.eventconsumer.elastic.HistoricActivityInstance";
	
	@Autowired
	private HistoricActivityInstanceRepository repository;
	
	@StreamListener(value = ProcessEngineEventChannels.ELASTIC_IN, condition = "headers['elasticType']=='"+HANDABLE_EVENT_TYPE+"'")	
	public void handle(HistoricActivityInstance message) {
		repository.save(message);
	}
}
