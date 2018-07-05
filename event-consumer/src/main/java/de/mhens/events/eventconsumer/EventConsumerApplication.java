package de.mhens.events.eventconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

@EnableCircuitBreaker
@SpringBootApplication
public class EventConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventConsumerApplication.class, args);
	}
}
