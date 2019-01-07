package de.mhens.events.integration;

import org.springframework.cloud.stream.annotation.EnableBinding;

@EnableBinding(value = {ProcessEngineEventsChannel.class})
public class IntegrationConfiguration {

}
