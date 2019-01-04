package de.mhens.events.eventconsumer.integration.processinstance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import de.mhens.events.eventconsumer.elastic.processinstance.HistoricProcessInstance;
import de.mhens.events.eventconsumer.integration.ProcessEngineEventChannels;

@Component
public class ProcessInstanceDurationEnricher {

	private final String HANDABLE_EVENT_TYPE="HistoricProcessInstanceEventEntity";
	
	@Autowired
	private ProcessEngineEventChannels channels;
 
	
	@StreamListener(value = ProcessEngineEventChannels.PROCESS_EVENTS, condition = "headers['historyEventType']=='"+HANDABLE_EVENT_TYPE+"'")
	public void enrich(Message<HistoricProcessInstance> message) {
		HistoricProcessInstance historicProcessInstance = message.getPayload();
		if(historicProcessInstance.isEnded()) {
			historicProcessInstance.computeDuration();
		}
		sendToChannel(message, historicProcessInstance);
	}
	
	private void sendToChannel(Message<HistoricProcessInstance> message, HistoricProcessInstance payload) {
		
		Message<HistoricProcessInstance> enrichedMessage =  MessageBuilder.withPayload(payload)
				 .copyHeaders(message.getHeaders())
				 .setHeader("elasticType", payload.getClass().getName())
				 .build();
		channels.processInstancesOut().send(enrichedMessage);
	}
	
	
}
