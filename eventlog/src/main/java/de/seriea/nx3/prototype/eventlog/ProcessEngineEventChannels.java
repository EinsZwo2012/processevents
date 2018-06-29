package de.seriea.nx3.prototype.eventlog;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface ProcessEngineEventChannels {
	
	String VARIABLES_OUT = "variables";
	
	String ACTIVITIES_OUT = "activities";
	
	String PROCESS_INSTANCES_OUT = "processinstances";

	@Output(ProcessEngineEventChannels.VARIABLES_OUT)
	MessageChannel variables();
	
	@Output(ProcessEngineEventChannels.ACTIVITIES_OUT)
	MessageChannel activities();
	
	@Output(ProcessEngineEventChannels.PROCESS_INSTANCES_OUT)
	MessageChannel processinstances();
}
