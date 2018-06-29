package de.seriea.nx3.prototype.eventconsumer;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface ProcessEngineEventChannels {
	
	String VARIABLES_IN = "variables";
	
	String ACTIVITIES_IN  ="activities";
	
	String PROCESS_INSTANCES_IN  ="processinstances";

	@Input(ProcessEngineEventChannels.VARIABLES_IN)
	SubscribableChannel variables();
	
	@Input(ProcessEngineEventChannels.ACTIVITIES_IN)
	SubscribableChannel activities();

	@Input(ProcessEngineEventChannels.PROCESS_INSTANCES_IN)
	SubscribableChannel processinstances();
}
