package de.mhens.events.eventconsumer.integration.processinstance;

import org.camunda.bpm.engine.impl.history.event.HistoricProcessInstanceEventEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import de.mhens.events.eventconsumer.elastic.processinstance.HistoricProcessInstance;
import de.mhens.events.eventconsumer.integration.HistoricProcessEventTransformer;
import de.mhens.events.eventconsumer.integration.ProcessEngineEventChannels;

@Transactional
@Component
public class HistoricProcessInstanceTransformer extends HistoricProcessEventTransformer<HistoricProcessInstanceEventEntity, HistoricProcessInstance> {
	
	private final String HANDABLE_EVENT_TYPE="org.camunda.bpm.engine.impl.history.event.HistoricProcessInstanceEventEntity";
	
	@Autowired
	public HistoricProcessInstanceTransformer(ProcessEngineEventChannels channels) {
		super(channels);
	}
	
	@HystrixCommand
	@StreamListener(value = ProcessEngineEventChannels.PROCESS_EVENTS, condition = "headers['historyEventType']=='"+HANDABLE_EVENT_TYPE+"'")
	public void transform(Message<HistoricProcessInstanceEventEntity> message) {
		super.transform(message);
	}

	@Override
	protected HistoricProcessInstance buildPayload(HistoricProcessInstanceEventEntity input) {

		return new HistoricProcessInstance(
				input.getId(), 
				input.getProcessInstanceId(),
				input.getStartTime(),
				input.getEndTime());
	}
}
