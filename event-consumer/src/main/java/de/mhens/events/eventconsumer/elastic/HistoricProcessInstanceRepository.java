package de.mhens.events.eventconsumer.elastic;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface HistoricProcessInstanceRepository extends ElasticsearchRepository<HistoricProcessInstance, String> {

}
