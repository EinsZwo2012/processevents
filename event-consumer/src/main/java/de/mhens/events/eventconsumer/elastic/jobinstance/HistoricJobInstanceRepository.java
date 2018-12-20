package de.mhens.events.eventconsumer.elastic.jobinstance;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface HistoricJobInstanceRepository extends ElasticsearchRepository<HistoricJobInstance, String> {

}
