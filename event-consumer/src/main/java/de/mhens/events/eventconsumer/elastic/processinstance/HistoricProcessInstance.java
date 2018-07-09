package de.mhens.events.eventconsumer.elastic.processinstance;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Document(indexName = "camunda", type = "historic-process")
public class HistoricProcessInstance {
 
    @Id
    private String id;
     
    private String processInstanceId;
     
    private Date startTime;
    
    private Date endTime;
}
