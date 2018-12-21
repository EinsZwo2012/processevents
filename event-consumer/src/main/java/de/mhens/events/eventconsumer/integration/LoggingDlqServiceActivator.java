package de.mhens.events.eventconsumer.integration;

import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class LoggingDlqServiceActivator {

	@ServiceActivator(inputChannel = ProcessEngineEventChannels.PROCESS_EVENT_ERRORS)
	public void logProcessEventDlq(Message<?> message) {
		log.error("Handling ERROR: {}", message);
	}
}
