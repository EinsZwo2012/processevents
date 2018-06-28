package de.seriea.nx3.prototype.eventlog;

import org.camunda.bpm.engine.impl.history.event.HistoricActivityInstanceEventEntity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

@EnableBinding(value = {ProcessEngineEventChannels.class, TestChannel.class})
@SpringBootApplication
public class EventlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventlogApplication.class, args);
	}
	
	@StreamListener(TestChannel.OUTPUT)
	public void handle(String name) {
		System.out.println("Received: " + name);
	}

	@StreamListener(target = ProcessEngineEventChannels.ACTIVITIES_IN
			, condition = "headers['historyEventType']=='org.camunda.bpm.engine.impl.history.event.HistoricActivityInstanceEventEntity'")
	public void test(HistoricActivityInstanceEventEntity event) {
		System.out.println("Received: " + event);
	}
}
