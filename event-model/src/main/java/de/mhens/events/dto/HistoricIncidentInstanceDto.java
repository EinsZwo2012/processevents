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

    protected String incidentType;
    
    protected String activityId;
    
    protected String causeIncidentId;
    
    protected String rootCauseIncidentId;
    
    protected String configuration;
    
    protected String incidentMessage;
    
    protected int incidentState;
    
    protected String jobDefinitionId;
}
