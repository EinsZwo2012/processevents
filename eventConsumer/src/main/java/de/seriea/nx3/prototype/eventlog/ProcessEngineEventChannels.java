package de.seriea.nx3.prototype.eventlog;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface ProcessEngineEventChannels {
	
	String VARIABLES_IN = "variables";
	
	String ACTIVITIES_IN  ="activities";

	@Input(ProcessEngineEventChannels.VARIABLES_IN)
	SubscribableChannel variables();
	
	@Input(ProcessEngineEventChannels.ACTIVITIES_IN)
	SubscribableChannel activities();
}
