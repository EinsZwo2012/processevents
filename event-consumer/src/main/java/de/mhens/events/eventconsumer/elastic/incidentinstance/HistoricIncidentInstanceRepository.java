package de.mhens.events.eventconsumer.elastic.incidentinstance;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface HistoricIncidentInstanceRepository extends ElasticsearchRepository<HistoricIncidentInstance, String> {

}
