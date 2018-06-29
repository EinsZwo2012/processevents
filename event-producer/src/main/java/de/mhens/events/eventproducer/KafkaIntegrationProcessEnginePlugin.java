package de.mhens.events.eventproducer;

import org.camunda.bpm.engine.impl.cfg.AbstractProcessEnginePlugin;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.camunda.bpm.engine.impl.history.handler.CompositeHistoryEventHandler;
import org.camunda.bpm.engine.impl.history.handler.DbHistoryEventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class KafkaIntegrationProcessEnginePlugin extends AbstractProcessEnginePlugin {

	@Autowired
	private KafkaHistoryEventHandler kafkaHistoryEventHandler;

	public void postInit(ProcessEngineConfigurationImpl processEngineConfiguration) {

		CompositeHistoryEventHandler compositeEventHandler = new CompositeHistoryEventHandler();

		DbHistoryEventHandler dbHistoryEventHandler = new DbHistoryEventHandler();
		compositeEventHandler.add(dbHistoryEventHandler);
		compositeEventHandler.add(kafkaHistoryEventHandler);
		
		processEngineConfiguration.setHistoryEventHandler(compositeEventHandler);
	}
}
