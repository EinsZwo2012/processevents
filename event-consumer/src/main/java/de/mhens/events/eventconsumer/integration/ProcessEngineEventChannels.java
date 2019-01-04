package de.mhens.events.eventconsumer.integration;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface ProcessEngineEventChannels {
	
	String PROCESS_EVENTS = "processevents";
	
	String PROCESS_INSTANCES_OUT = "processinstancesOut";
	
	String PROCESS_INSTANCES_IN = "processinstancesIn";
	
	String PROCESS_EVENT_ERRORS="processeventerrors";
	
	String PROCESS_INSTANCES_ERRORS="processinstanceserrors";
	
	@Input(ProcessEngineEventChannels.PROCESS_EVENTS)
	SubscribableChannel processEvents();
	
	@Input(ProcessEngineEventChannels.PROCESS_EVENT_ERRORS)
	SubscribableChannel processEventErrors();
	
	@Input(ProcessEngineEventChannels.PROCESS_INSTANCES_IN)
	SubscribableChannel processInstancesIn();
	
	@Output(ProcessEngineEventChannels.PROCESS_INSTANCES_OUT)
	MessageChannel processInstancesOut();
}
