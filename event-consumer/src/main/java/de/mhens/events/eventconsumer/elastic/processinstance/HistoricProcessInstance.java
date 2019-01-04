package de.mhens.events.eventconsumer.elastic.processinstance;

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
@Document(indexName = "camunda", type = "historic-process-instance")
public class HistoricProcessInstance {
 
    @Id
    private String id;
     
    private String processInstanceId;
    
    private String businessKey;
    
    private String startActivityId;
    
    private String endActivityId;
    
    private String deleteReason;
     
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
    
    @Field(
			type = FieldType.Long,
			store = true
	)
    private Long durationInMs;
    
    public boolean isEnded(){
    	return endTime!=null;
    }
    
    public void computeDuration() {
    	if(endTime == null) {
    		throw new IllegalStateException("EndTime must be not null");
    	}else {
    		durationInMs = endTime.getTime() - startTime.getTime();
    	}
    }
}
