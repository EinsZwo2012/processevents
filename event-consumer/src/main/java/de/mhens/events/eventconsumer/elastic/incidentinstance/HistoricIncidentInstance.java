package de.mhens.events.eventconsumer.elastic.incidentinstance;

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
@Document(indexName = "camunda-historic-incident-instance")
public class HistoricIncidentInstance {
 
    @Id
    private String id;
          
    @Field(
			type = FieldType.Date,
			store = true
	)
    private Date createTime;
    
    @Field(
			type = FieldType.Date,
			store = true
	)
    private Date endTime;    

    protected String incidentType;
    
    protected String activityId;
    
    protected String causeIncidentId;
    
    protected String rootCauseIncidentId;
    
    protected String configuration;
    
    protected String incidentMessage;
    
    protected int incidentState;
    
    protected String jobDefinitionId;
    
    private String processInstanceId;

}
