package de.seriea.nx3.eventconsumer.integration;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface ProcessEngineEventChannels {
	
	String PROCESS_EVENTS = "processevents";

	String ELASTIC = "elastic";
	
	@Input(ProcessEngineEventChannels.PROCESS_EVENTS)
	SubscribableChannel processEvents();
	
	@Output(ProcessEngineEventChannels.ELASTIC)
	MessageChannel elastic();
}
