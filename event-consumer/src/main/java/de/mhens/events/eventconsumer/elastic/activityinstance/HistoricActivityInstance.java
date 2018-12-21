package de.mhens.events.eventconsumer.elastic.activityinstance;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Document(indexName = "camunda", type = "historic-activity")
public class HistoricActivityInstance {
 
    @Id
    private String id;
     
    private String processInstanceId;
    
    @Field(
			type = FieldType.Date,
			store = true
	)
    private Date startTime;
    
    @Field(
			type = FieldType.Date,
			store = true
	)
    private Date endTime;
    
    private String activityId;
  
    private String activityName;

    private String activityType;

    private String activityInstanceId;

    private int activityInstanceState;
}
