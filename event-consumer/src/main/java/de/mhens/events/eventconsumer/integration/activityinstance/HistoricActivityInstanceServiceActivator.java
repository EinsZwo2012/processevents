package de.mhens.events.eventconsumer.integration.activityinstance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import de.mhens.events.eventconsumer.elastic.taskinstance.HistoricTaskInstance;
import de.mhens.events.eventconsumer.elastic.taskinstance.HistoricTaskInstanceRepository;
import de.mhens.events.eventconsumer.integration.ProcessEngineEventChannels;

@Transactional
@Component
public class HistoricActivityInstanceServiceActivator {

	private final String HANDABLE_EVENT_TYPE="de.mhens.events.eventconsumer.elastic.HistoricTaskInstance";
	
	@Autowired
	private HistoricTaskInstanceRepository repository;
	
	@StreamListener(value = ProcessEngineEventChannels.ELASTIC_IN, condition = "headers['elasticType']=='"+HANDABLE_EVENT_TYPE+"'")	
	public void handle(HistoricTaskInstance message) {
		repository.save(message);
	}
}
