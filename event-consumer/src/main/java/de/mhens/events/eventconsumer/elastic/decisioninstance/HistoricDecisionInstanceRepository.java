package de.mhens.events.eventconsumer.elastic.decisioninstance;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface HistoricDecisionInstanceRepository extends ElasticsearchRepository<HistoricDecisionInstance, String> {

}
