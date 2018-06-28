package de.seriea.nx3.prototype.eventlog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Component
@Transactional
public class Gateway {

	@Autowired
	private Processor processor;
	
	@HystrixCommand
	public String writeToKafka(String name) {
		System.out.println("Received: " + name);
		processor.output().send(MessageBuilder
									.withPayload(name)
									.setHeader("partitionKey", name)
									.build(), 2000l);
		
		//throw new RuntimeException("Test");
		return name;
	}
}
