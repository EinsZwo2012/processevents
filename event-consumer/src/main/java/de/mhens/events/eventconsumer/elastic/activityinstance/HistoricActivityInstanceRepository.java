package de.mhens.events.eventconsumer.elastic.activityinstance;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface HistoricActivityInstanceRepository extends ElasticsearchRepository<HistoricActivityInstance, String> {

}
