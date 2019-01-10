package de.mhens.events.eventconsumer.elastic.decisioninstance;

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
@Document(indexName = "camunda-historic-decision-instance")
public class HistoricDecisionInstance {
 
    @Id
    private String id;
     
    private String decisionDefinitionId;
    
    private String decisionDefinitionKey;
    
    private String decisionDefinitionName;

    private String activityInstanceId;
   
    private String activityId;
    
    private String rootDecisionInstanceId;
    
    private String decisionRequirementsDefinitionId;
    
    private String decisionRequirementsDefinitionKey;
    
    @Field(
			type = FieldType.Date,
			store = true
	)
    private Date evaluationTime;
}
