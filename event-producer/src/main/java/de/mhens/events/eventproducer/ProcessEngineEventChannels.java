package de.mhens.events.eventproducer;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface ProcessEngineEventChannels {
	
	String PROCESS_EVENTS = "processevents";

	@Output(ProcessEngineEventChannels.PROCESS_EVENTS)
	MessageChannel processEvents();
}
