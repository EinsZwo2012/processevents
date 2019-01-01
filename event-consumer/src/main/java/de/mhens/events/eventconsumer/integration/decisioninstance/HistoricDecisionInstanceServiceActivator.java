package de.mhens.events.eventconsumer.integration.decisioninstance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import de.mhens.events.eventconsumer.elastic.decisioninstance.HistoricDecisionInstance;
import de.mhens.events.eventconsumer.elastic.decisioninstance.HistoricDecisionInstanceRepository;
import de.mhens.events.eventconsumer.integration.ProcessEngineEventChannels;

@Transactional
@Component
public class HistoricDecisionInstanceServiceActivator {

	private final String HANDABLE_EVENT_TYPE="HistoricDecisionInstanceEntity";

	@Autowired
	private HistoricDecisionInstanceRepository repository;
	
	@HystrixCommand
	@StreamListener(value = ProcessEngineEventChannels.PROCESS_EVENTS, condition = "headers['historyEventType']=='"+HANDABLE_EVENT_TYPE+"'")
	public void handle(HistoricDecisionInstance message) {
		repository.save(message);
	}
}
