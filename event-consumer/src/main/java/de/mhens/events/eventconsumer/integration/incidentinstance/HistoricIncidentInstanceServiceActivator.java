package de.mhens.events.eventconsumer.integration.incidentinstance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import de.mhens.events.eventconsumer.elastic.incidentinstance.HistoricIncidentInstance;
import de.mhens.events.eventconsumer.elastic.incidentinstance.HistoricIncidentInstanceRepository;
import de.mhens.events.eventconsumer.integration.ProcessEngineEventChannels;

@Transactional
@Component
public class HistoricIncidentInstanceServiceActivator {

	private final String HANDABLE_EVENT_TYPE="HistoricIncidentEventEntity";

	@Autowired
	private HistoricIncidentInstanceRepository repository;
	
	@HystrixCommand
	@StreamListener(value = ProcessEngineEventChannels.PROCESS_EVENTS, condition = "headers['historyEventType']=='"+HANDABLE_EVENT_TYPE+"'")
	public void handle(HistoricIncidentInstance message) {
		repository.save(message);
	}
}
