package de.mhens.events.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class HistoricTaskInstanceDto extends EventDto{
 
    private String id;
     
    private String processInstanceId;
     
    private Date startTime;
    
    private Date endTime;
    
    private String taskId;
    
    private String taskDefinitionKey;
}
