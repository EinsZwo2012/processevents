package de.mhens.events.eventconsumer.integration;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface ProcessEngineEventChannels {
	
	String PROCESS_EVENTS = "processevents";
	
	String PROCESS_EVENT_ERRORS="processeventerrors";

	String ELASTIC_IN = "elasticIn";
	
	String ELASTIC_OUT= "elasticOut";
	
	String ELASTIC_ERRORS = "elasticerrors";
	
	@Input(ProcessEngineEventChannels.PROCESS_EVENTS)
	SubscribableChannel processEvents();
	
	@Input(ProcessEngineEventChannels.PROCESS_EVENT_ERRORS)
	SubscribableChannel processEventErrors();
	
	@Input(ProcessEngineEventChannels.ELASTIC_IN)
	SubscribableChannel elasticIn();
	
	@Output(ProcessEngineEventChannels.ELASTIC_OUT)
	MessageChannel elastic();
	
	@Input(ProcessEngineEventChannels.ELASTIC_ERRORS)
	SubscribableChannel elasticErrors();
}
