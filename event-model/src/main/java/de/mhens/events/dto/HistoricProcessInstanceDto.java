package de.mhens.events.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class HistoricProcessInstanceDto extends EventDto{
 
    private String id;
     
    private String processInstanceId;
    
    private String businessKey;
    
    private String startActivityId;
    
    private String endActivityId;
    
    private String deleteReason;
     
    private Date startTime;
    
    private Date endTime;
}
