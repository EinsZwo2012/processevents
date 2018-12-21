package de.mhens.events.eventconsumer.integration.taskinstance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import de.mhens.events.eventconsumer.elastic.taskinstance.HistoricTaskInstance;
import de.mhens.events.eventconsumer.elastic.taskinstance.HistoricTaskInstanceRepository;
import de.mhens.events.eventconsumer.integration.ProcessEngineEventChannels;

@Transactional
@Component
public class HistoricTaskInstanceServiceActivator {

	private final String HANDABLE_EVENT_TYPE="HistoricTaskInstanceEventEntity";

	@Autowired
	private HistoricTaskInstanceRepository repository;
	
	@HystrixCommand
	@StreamListener(value = ProcessEngineEventChannels.PROCESS_EVENTS, condition = "headers['historyEventType']=='"+HANDABLE_EVENT_TYPE+"'")
	public void handle(HistoricTaskInstance message) {
		repository.save(message);
	}
}
