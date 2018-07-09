package de.mhens.events.eventconsumer.integration.taskinstance;

import org.camunda.bpm.engine.impl.persistence.entity.HistoricTaskInstanceEntity;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import de.mhens.events.eventconsumer.elastic.taskinstance.HistoricTaskInstance;
import de.mhens.events.eventconsumer.integration.HistoricProcessEventTransformer;
import de.mhens.events.eventconsumer.integration.ProcessEngineEventChannels;

@Transactional
@Component
public class HistoricTaskInstanceTransformer extends HistoricProcessEventTransformer<HistoricTaskInstanceEntity, HistoricTaskInstance>{
	
	private final String HANDABLE_EVENT_TYPE="org.camunda.bpm.engine.impl.history.event.HistoricTaskInstanceEventEntity";
	
	@HystrixCommand
	@StreamListener(value = ProcessEngineEventChannels.PROCESS_EVENTS, condition = "headers['historyEventType']=='"+HANDABLE_EVENT_TYPE+"'")
	public void transform(Message<HistoricTaskInstanceEntity> message) {
		super.transform(message);
	}

	@Override
	protected HistoricTaskInstance buildPayload(HistoricTaskInstanceEntity input) {
		HistoricTaskInstance payload = new HistoricTaskInstance(
				input.getId(), 
				input.getProcessInstanceId(),
				input.getStartTime(),
				input.getEndTime(),
				input.getTaskId());
		
		return payload;
	}
}
