package de.mhens.events.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class HistoricIncidentInstanceDto extends EventDto{
 
    private String id;
          
    private Date createTime;
    
    private Date endTime;    

    private String incidentType;
    
    private String activityId;
    
    private String causeIncidentId;
    
    private String rootCauseIncidentId;
    
    private String configuration;
    
    private String incidentMessage;
    
    private int incidentState;
    
    private String jobDefinitionId;
    
    private String processInstanceId;

}
