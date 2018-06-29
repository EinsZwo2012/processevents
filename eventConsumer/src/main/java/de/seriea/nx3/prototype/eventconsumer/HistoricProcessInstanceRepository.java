package de.seriea.nx3.prototype.eventconsumer;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface HistoricProcessInstanceRepository extends ElasticsearchRepository<HistoricProcessInstance, String> {

}
