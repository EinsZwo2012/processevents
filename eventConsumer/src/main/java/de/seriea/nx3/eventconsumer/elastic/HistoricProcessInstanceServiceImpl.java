package de.seriea.nx3.eventconsumer.elastic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class HistoricProcessInstanceServiceImpl implements HistoricProcessInstanceService {
	
	@Autowired
	private HistoricProcessInstanceRepository repository;

	@Override
	public void save(HistoricProcessInstance entity) {
		repository.save(entity);	
	}
}
