package de.mhens.events.eventconsumer.integration.activityinstance;

import org.camunda.bpm.engine.impl.persistence.entity.HistoricActivityInstanceEntity;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import de.mhens.events.eventconsumer.elastic.activityinstance.HistoricActivityInstance;
import de.mhens.events.eventconsumer.integration.HistoricProcessEventTransformer;
import de.mhens.events.eventconsumer.integration.ProcessEngineEventChannels;

@Transactional
@Component
public class HistoricActivityInstanceTransformer extends HistoricProcessEventTransformer<HistoricActivityInstanceEntity, HistoricActivityInstance> {
	
	private final String HANDABLE_EVENT_TYPE="org.camunda.bpm.engine.impl.history.event.HistoricActivityInstanceEventEntity";
	
	@HystrixCommand
	@StreamListener(value = ProcessEngineEventChannels.PROCESS_EVENTS, condition = "headers['historyEventType']=='"+HANDABLE_EVENT_TYPE+"'")
	public void transform(Message<HistoricActivityInstanceEntity> message) {
		super.transform(message);
	}

	@Override
	protected HistoricActivityInstance buildPayload(HistoricActivityInstanceEntity input) {
		HistoricActivityInstance payload = new HistoricActivityInstance(
				input.getId(), 
				input.getProcessInstanceId(),
				input.getStartTime(),
				input.getEndTime(),
				input.getActivityId());
		
		return payload;
	}
}
