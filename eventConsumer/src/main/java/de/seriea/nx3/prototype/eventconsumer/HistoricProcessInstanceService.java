package de.seriea.nx3.prototype.eventconsumer;

import org.camunda.bpm.engine.impl.history.event.HistoricProcessInstanceEventEntity;

public interface HistoricProcessInstanceService {

	HistoricProcessInstance createFrom(HistoricProcessInstanceEventEntity event);

	void save(HistoricProcessInstance entity);

}