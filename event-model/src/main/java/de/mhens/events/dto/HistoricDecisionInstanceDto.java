package de.mhens.events.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class HistoricDecisionInstanceDto  extends EventDto{
 
    private String id;
     
    private String decisionDefinitionId;
    
    private String decisionDefinitionKey;
    
    private String decisionDefinitionName;

    private String activityInstanceId;
   
    private String activityId;
    
    private String rootDecisionInstanceId;
    
    private String decisionRequirementsDefinitionId;
    
    private String decisionRequirementsDefinitionKey;
    
    private Date evaluationTime;
    
    private String processInstanceId;
}
