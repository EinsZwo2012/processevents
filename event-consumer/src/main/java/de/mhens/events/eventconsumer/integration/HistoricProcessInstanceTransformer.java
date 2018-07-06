package de.mhens.events.eventconsumer.integration;

import org.camunda.bpm.engine.impl.history.event.HistoricProcessInstanceEventEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import de.mhens.events.eventconsumer.elastic.HistoricProcessInstance;

@Transactional
@Component
public class HistoricProcessInstanceTransformer {
	
	private final String HANDABLE_EVENT_TYPE="org.camunda.bpm.engine.impl.history.event.HistoricProcessInstanceEventEntity";
	
	@Autowired
	private ProcessEngineEventChannels channels;
	
	@HystrixCommand
	@StreamListener(value = ProcessEngineEventChannels.PROCESS_EVENTS, condition = "headers['historyEventType']=='"+HANDABLE_EVENT_TYPE+"'")
	public void transform(Message<HistoricProcessInstanceEventEntity> message) {
		
		HistoricProcessInstanceEventEntity historicProcessInstaneEvent = message.getPayload();
		
		HistoricProcessInstance instance = new HistoricProcessInstance(
				historicProcessInstaneEvent.getId(), 
				historicProcessInstaneEvent.getProcessInstanceId(),
				historicProcessInstaneEvent.getStartTime(),
				historicProcessInstaneEvent.getEndTime());
		
		Message<HistoricProcessInstance> transformedMessage =  MessageBuilder.withPayload(instance)
							 .copyHeaders(message.getHeaders())
							 .setHeader("elasticType", HistoricProcessInstance.class.getName())
							 .build();
		
		channels.elasticOut().send(transformedMessage);
	}
	
	@ServiceActivator(inputChannel = ProcessEngineEventChannels.PROCESS_EVENT_ERRORS)
	public void error(Message<?> message) {
		System.out.println("Handling ERROR: " + message);
	}
}
