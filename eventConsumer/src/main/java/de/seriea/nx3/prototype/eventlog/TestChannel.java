package de.seriea.nx3.prototype.eventlog;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface TestChannel {

	String OUTPUT ="output";

	@Input(TestChannel.OUTPUT)
	SubscribableChannel input();
}
