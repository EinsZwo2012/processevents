package de.mhens.events.eventconsumer.elastic.processinstance;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface HistoricProcessInstanceRepository extends ElasticsearchRepository<HistoricProcessInstance, String> {

}
