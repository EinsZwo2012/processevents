package de.mhens.events.eventconsumer.integration.jobinstance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import de.mhens.events.eventconsumer.elastic.jobinstance.HistoricJobInstance;
import de.mhens.events.eventconsumer.elastic.jobinstance.HistoricJobInstanceRepository;
import de.mhens.events.eventconsumer.integration.ProcessEngineEventChannels;

@Transactional
@Component
public class HistoricJobInstanceServiceActivator {

	private final String HANDABLE_EVENT_TYPE="HistoricJobLogEventEntity";
	
	@Autowired
	private HistoricJobInstanceRepository repository;
	
	@HystrixCommand
	@StreamListener(value = ProcessEngineEventChannels.PROCESS_EVENTS, condition = "headers['historyEventType']=='"+HANDABLE_EVENT_TYPE+"'")
	public void handle(HistoricJobInstance message) {
		repository.save(message);
	}
}
