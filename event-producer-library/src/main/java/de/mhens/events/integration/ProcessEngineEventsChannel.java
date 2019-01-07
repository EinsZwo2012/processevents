package de.mhens.events.integration;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface ProcessEngineEventsChannel {
	
	String PROCESS_EVENTS = "processevents";

	@Output(ProcessEngineEventsChannel.PROCESS_EVENTS)
	MessageChannel processEvents();
}
