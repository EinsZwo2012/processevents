package de.seriea.nx3.prototype.eventlog;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface ProcessEngineEventChannels {
	
	String PROCESS_EVENTS = "processevents";

	@Output(ProcessEngineEventChannels.PROCESS_EVENTS)
	MessageChannel processEvents();
}
