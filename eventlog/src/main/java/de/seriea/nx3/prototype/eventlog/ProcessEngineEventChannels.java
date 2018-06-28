package de.seriea.nx3.prototype.eventlog;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface ProcessEngineEventChannels {
	
	String VARIABLES_OUT = "variables";
	
	String ACTIVITIES_OUT = "activities";

	@Output(ProcessEngineEventChannels.VARIABLES_OUT)
	MessageChannel variables();
	
	@Output(ProcessEngineEventChannels.ACTIVITIES_OUT)
	MessageChannel activities();
}
