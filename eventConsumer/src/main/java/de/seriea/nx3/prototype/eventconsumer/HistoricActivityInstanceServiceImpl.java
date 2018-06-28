package de.seriea.nx3.prototype.eventconsumer;

import org.camunda.bpm.engine.impl.history.event.HistoricActivityInstanceEventEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class HistoricActivityInstanceServiceImpl {
	
	@Autowired
	private HistoricActivityInstanceRepository repository;
	
	public HistoricActivityInstance createFrom(HistoricActivityInstanceEventEntity event) {
		HistoricActivityInstance instance = new HistoricActivityInstance(event.getId(), event.getActivityId());
		
		return instance;
	}
	
	public void save(HistoricActivityInstance entity) {
		repository.save(entity);	
	}
}
