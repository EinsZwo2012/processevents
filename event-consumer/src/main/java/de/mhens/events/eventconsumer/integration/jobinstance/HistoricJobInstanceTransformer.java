package de.mhens.events.eventconsumer.integration.jobinstance;

import org.camunda.bpm.engine.impl.persistence.entity.HistoricJobLogEventEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import de.mhens.events.eventconsumer.elastic.jobinstance.HistoricJobInstance;
import de.mhens.events.eventconsumer.integration.HistoricProcessEventTransformer;
import de.mhens.events.eventconsumer.integration.ProcessEngineEventChannels;

@Transactional
@Component
public class HistoricJobInstanceTransformer extends HistoricProcessEventTransformer<HistoricJobLogEventEntity, HistoricJobInstance>{
	
	private final String HANDABLE_EVENT_TYPE="org.camunda.bpm.engine.impl.persistence.entity.HistoricJobLogEventEntity";
	
	@Autowired
	public HistoricJobInstanceTransformer(ProcessEngineEventChannels channels) {
		super(channels);
	}
	
	@HystrixCommand
	@StreamListener(value = ProcessEngineEventChannels.PROCESS_EVENTS, condition = "headers['historyEventType']=='"+HANDABLE_EVENT_TYPE+"'")
	public void transform(Message<HistoricJobLogEventEntity> message) {
		super.transform(message);
	}

	@Override
	protected HistoricJobInstance buildPayload(HistoricJobLogEventEntity input) {
		HistoricJobInstance payload = new HistoricJobInstance(
				input.getId(),
				input.getActivityId(),
				input.getState(),
				input.getProcessInstanceId(),
				input.getJobDueDate()
		);
		
		return payload;
	}
}
