package de.mhens.events.eventconsumer.integration;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Configuration;

@EnableBinding(value = {ProcessEngineEventChannels.class})
@Configuration
public class IntegrationConfiguration {

}
