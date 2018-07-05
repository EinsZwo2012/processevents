package de.mhens.events.eventconsumer;

import java.util.UUID;

import org.camunda.bpm.engine.impl.history.event.HistoricProcessInstanceEventEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.test.binder.MessageCollector;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
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
	public void testIntegrationPipeline() {
		
		HistoricProcessInstanceEventEntity event = new HistoricProcessInstanceEventEntity();
		event.setId(UUID.randomUUID().toString());
		event.setProcessInstanceId(UUID.randomUUID().toString());
		
		Message<HistoricProcessInstanceEventEntity> message = new GenericMessage<>(event);
		channels.processEvents().send(message);
		Message<String> received = 
				(Message<String>) messageCollector.forChannel(channels.elasticOut()).poll();
		
		Assert.isTrue(received.getPayload().contains(event.getId()), String.format("Received event must have id=%s ", event.getId()));
	}
}
