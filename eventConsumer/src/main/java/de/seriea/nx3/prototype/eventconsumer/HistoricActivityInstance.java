package de.seriea.nx3.prototype.eventconsumer;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Document(indexName = "camunda", type = "processevent")
public class HistoricActivityInstance {
 
    @Id
    private String id;
     
    private String activityId;
     
}
