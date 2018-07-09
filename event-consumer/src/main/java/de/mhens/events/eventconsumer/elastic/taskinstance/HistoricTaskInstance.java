package de.mhens.events.eventconsumer.elastic.taskinstance;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Document(indexName = "camunda", type = "historic-task")
public class HistoricTaskInstance {
 
    @Id
    private String id;
     
    private String processInstanceId;
     
    private Date startTime;
    
    private Date endTime;
    
    private String taskId;
}
