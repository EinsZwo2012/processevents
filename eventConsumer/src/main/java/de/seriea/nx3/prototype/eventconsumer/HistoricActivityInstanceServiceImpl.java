package de.seriea.nx3.prototype.eventconsumer;

import java.util.Optional;

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
		Optional<HistoricActivityInstance> findById = repository.findById("StartEvent_1:3abd589b-7aef-11e8-a4c6-acde48001122");
		
		findById.isPresent();
	}
}
