package de.mhens.events.eventconsumer.elastic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
@Transactional
public class HistoricProcessInstanceServiceImpl implements HistoricProcessInstanceService {
	
	@Autowired
	private HistoricProcessInstanceRepository repository;

	@HystrixCommand
	@Override
	public void save(HistoricProcessInstance entity) {
		repository.save(entity);
	}
}
