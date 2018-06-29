package de.seriea.nx3.prototype.eventconsumer;

import org.camunda.bpm.engine.impl.history.event.HistoricProcessInstanceEventEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class HistoricProcessInstanceServiceImpl implements HistoricProcessInstanceService {
	
	@Autowired
	private HistoricProcessInstanceRepository repository;
	
	@Override
	public HistoricProcessInstance createFrom(HistoricProcessInstanceEventEntity event) {
		HistoricProcessInstance instance = new HistoricProcessInstance(
													event.getId(), 
													event.getProcessInstanceId(),
													event.getStartTime(),
													event.getEndTime()
				);
		
		return instance;
	}

	@Override
	public void save(HistoricProcessInstance entity) {
		repository.save(entity);	
	}
}
