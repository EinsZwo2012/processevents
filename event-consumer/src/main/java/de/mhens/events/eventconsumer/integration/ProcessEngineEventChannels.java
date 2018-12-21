package de.mhens.events.eventconsumer.integration;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface ProcessEngineEventChannels {
	
	String PROCESS_EVENTS = "processevents";
	
	String PROCESS_EVENT_ERRORS="processeventerrors";
	
	@Input(ProcessEngineEventChannels.PROCESS_EVENTS)
	SubscribableChannel processEvents();
	
	@Input(ProcessEngineEventChannels.PROCESS_EVENT_ERRORS)
	SubscribableChannel processEventErrors();
}
