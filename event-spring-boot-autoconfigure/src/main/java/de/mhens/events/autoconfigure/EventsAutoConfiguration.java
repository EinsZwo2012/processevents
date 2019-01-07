package de.mhens.events.autoconfigure;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import de.mhens.events.integration.ChannelHistoryEventHandler;
import de.mhens.events.integration.ProcessEngineEventsChannel;
import de.mhens.events.integration.transformers.HistoricActivityInstanceEventTransformer;
import de.mhens.events.integration.transformers.HistoricDecisionEvaluationEventTransformer;
import de.mhens.events.integration.transformers.HistoricIncidentEventTransformer;
import de.mhens.events.integration.transformers.HistoricJobLogEventTransformer;
import de.mhens.events.integration.transformers.HistoricProcessInstanceEventTransformer;
import de.mhens.events.integration.transformers.HistoricTaskInstanceEventTransformer;

@Configuration
@EnableConfigurationProperties(EventProperties.class)
@EnableBinding(value = {ProcessEngineEventsChannel.class})
@EnableCircuitBreaker
public class EventsAutoConfiguration {

	@Bean
	@ConditionalOnMissingBean
	public ChannelHistoryEventHandler channelHistoryEventHandler(ProcessEngineEventsChannel channels,
			  													 ApplicationContext ctx) {
		return new ChannelHistoryEventHandler(channels,ctx);   
	}
	
	@Bean
	@ConditionalOnMissingBean
	public HistoricActivityInstanceEventTransformer historicActivityInstanceEventTransformer() {
		return new HistoricActivityInstanceEventTransformer();
	}
	
	@Bean
	@ConditionalOnMissingBean
	public HistoricDecisionEvaluationEventTransformer historicDecisionEvaluationEventTransformer() {
		return new HistoricDecisionEvaluationEventTransformer();
	}
	
	@Bean
	@ConditionalOnMissingBean
	public HistoricIncidentEventTransformer historicIncidentEventTransformer() {
		return new HistoricIncidentEventTransformer();
	}
	
	@Bean
	@ConditionalOnMissingBean
	public HistoricJobLogEventTransformer historicJobLogEventTransformer() {
		return new HistoricJobLogEventTransformer();
	}
	
	@Bean
	@ConditionalOnMissingBean
	public HistoricProcessInstanceEventTransformer historicProcessInstanceEventTransformer() {
		return new HistoricProcessInstanceEventTransformer();
	}
	
	@Bean
	@ConditionalOnMissingBean
	public HistoricTaskInstanceEventTransformer historicTaskInstanceEventTransformer() {
		return new HistoricTaskInstanceEventTransformer();
	}
}
