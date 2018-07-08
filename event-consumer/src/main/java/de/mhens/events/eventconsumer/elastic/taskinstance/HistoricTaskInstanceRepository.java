package de.mhens.events.eventconsumer.elastic.taskinstance;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface HistoricTaskInstanceRepository extends ElasticsearchRepository<HistoricTaskInstance, String> {

}
