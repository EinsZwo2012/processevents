package de.mhens.events.eventconsumer;

import java.util.UUID;

import org.camunda.bpm.engine.impl.history.event.HistoricActivityInstanceEventEntity;
import org.camunda.bpm.engine.impl.history.event.HistoricProcessInstanceEventEntity;
import org.camunda.bpm.engine.impl.history.event.HistoricTaskInstanceEventEntity;
import org.camunda.bpm.engine.impl.history.event.HistoryEvent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.test.binder.MessageCollector;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import de.mhens.events.eventconsumer.integration.ProcessEngineEventChannels;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EventConsumerApplicationTests {

	@Autowired
	private ProcessEngineEventChannels channels;

	@Autowired
	private MessageCollector messageCollector;

	@Test
	@SuppressWarnings("unchecked")
	public void testProcessInstanceIntegrationPipeline() {
		//GIVEN
		HistoricProcessInstanceEventEntity event = new HistoricProcessInstanceEventEntity();
		event.setId(UUID.randomUUID().toString());
		event.setProcessInstanceId(UUID.randomUUID().toString());
		
		Message<?> message = buildMessage(event);
		
		//WHEN
		sendToChannel(message);
		
		//THEN
		Message<String> received = 
				(Message<String>) messageCollector.forChannel(channels.elasticOut()).poll();
		
		Assert.isTrue(received.getPayload().contains(event.getId()), String.format("Received event must have id=%s ", event.getId()));
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void testTaskInstanceIntegrationPipeline() {
		//GIVEN
		HistoricTaskInstanceEventEntity event = new HistoricTaskInstanceEventEntity();
		event.setId(UUID.randomUUID().toString());
		event.setProcessInstanceId(UUID.randomUUID().toString());
		event.setTaskId(UUID.randomUUID().toString());
		
		Message<?> message = buildMessage(event);
		
		//WHEN
		sendToChannel(message);
		
		//THEN
		Message<String> received = 
				(Message<String>) messageCollector.forChannel(channels.elasticOut()).poll();
		
		Assert.isTrue(received.getPayload().contains(event.getId()), String.format("Received event must have id=%s ", event.getId()));
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void testActivityInstanceIntegrationPipeline() {
		//GIVEN
		HistoricActivityInstanceEventEntity event = new HistoricActivityInstanceEventEntity();
		event.setId(UUID.randomUUID().toString());
		event.setProcessInstanceId(UUID.randomUUID().toString());
		
		Message<?> message = buildMessage(event);
		
		//WHEN
		sendToChannel(message);
		
		//THEN
		Message<String> received = 
				(Message<String>) messageCollector.forChannel(channels.elasticOut()).poll();
		
		Assert.isTrue(received.getPayload().contains(event.getId()), String.format("Received event must have id=%s ", event.getId()));
	}

	private void sendToChannel(Message<?> message) {
		channels.processEvents().send(message);
	}

	private Message<?> buildMessage(HistoryEvent event) {
		Message<?> message = MessageBuilder
				.withPayload(event)
				.setHeader("historyEventType", event.getClass().getName())
				.build();
		return message;
	}
}
