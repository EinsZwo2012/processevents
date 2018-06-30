package de.mhens.events.eventproducer.integration;

import org.springframework.cloud.stream.annotation.EnableBinding;

@EnableBinding(value = {ProcessEngineEventChannels.class})
public class IntegrationConfiguration {

}
