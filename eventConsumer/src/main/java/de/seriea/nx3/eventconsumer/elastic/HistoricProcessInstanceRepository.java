package de.seriea.nx3.eventconsumer.elastic;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface HistoricProcessInstanceRepository extends ElasticsearchRepository<HistoricProcessInstance, String> {

}
