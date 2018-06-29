package de.mhens.events.eventproducer;

import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;

@EnableCircuitBreaker
@EnableBinding(value = {ProcessEngineEventChannels.class, Processor.class})
@EnableProcessApplication
@SpringBootApplication
public class EventProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventProducerApplication.class, args);
	}
}
