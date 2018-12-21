package de.mhens.events.eventconsumer.integration.activityinstance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import de.mhens.events.eventconsumer.elastic.activityinstance.HistoricActivityInstance;
import de.mhens.events.eventconsumer.elastic.activityinstance.HistoricActivityInstanceRepository;
import de.mhens.events.eventconsumer.integration.ProcessEngineEventChannels;

@Transactional
@Component
public class HistoricActivityInstanceServiceActivator {

	private final String HANDABLE_EVENT_TYPE="HistoricActivityInstanceEventEntity";
	
	@Autowired
	private HistoricActivityInstanceRepository repository;
	
	@HystrixCommand
	@StreamListener(value = ProcessEngineEventChannels.PROCESS_EVENTS, condition = "headers['historyEventType']=='"+HANDABLE_EVENT_TYPE+"'")
	public void handle(HistoricActivityInstance message) {
		repository.save(message);
	}
}
