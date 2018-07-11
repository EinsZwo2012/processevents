package de.mhens.events.eventconsumer.integration;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;

/**
 * Template method pattern to transform message with payload I to message with payload O
 * Spring integration calls it transformer instead of translator
 * 
 * @see http://www.enterpriseintegrationpatterns.com/patterns/messaging/MessageTranslator.html
 * @author markushens
 */
public abstract class HistoricProcessEventTransformer<I,O> {
	
	private ProcessEngineEventChannels channels;
	
	public HistoricProcessEventTransformer(ProcessEngineEventChannels channels) {
		this.channels = channels;
	}
	
	protected void transform(Message<I> message) {
		
		I historicProcessEvent = message.getPayload();
		
		O payload = buildPayload(historicProcessEvent);
		
		Message<O> transformedMessage =  buildMessage(payload, message.getHeaders());
		
		sendToChannel(transformedMessage);
	}
	
	private void sendToChannel(Message<O> message) {
		channels.elasticOut().send(message);
	}
	
	private Message<O> buildMessage(O payload, MessageHeaders headersToCopy) {
		Message<O> transformedMessage =  MessageBuilder.withPayload(payload)
				 .copyHeaders(headersToCopy)
				 .setHeader("elasticType", payload.getClass().getName())
				 .build();
		
		return transformedMessage;
	}
	
	protected abstract O buildPayload(I input);

}
