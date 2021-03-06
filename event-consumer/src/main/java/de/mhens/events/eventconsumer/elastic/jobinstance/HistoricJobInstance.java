package de.mhens.events.eventconsumer.elastic.jobinstance;

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
@Document(indexName = "camunda-historic-job-instance")
public class HistoricJobInstance {
 
    @Id
    private String id;
     
	private String activityId;
	
	private Integer state;

	private String processInstanceId;
	
    @Field(
			type = FieldType.Date,
			store = true
	)
    private Date jobDueDate;
}
