package de.mhens.events.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class HistoricActivityInstanceDto extends EventDto{
 
    private String id;
     
    private String processInstanceId;
    
    private Date startTime;
    
    private Date endTime;
    
    private String activityId;
  
    private String activityName;

    private String activityType;

    private String activityInstanceId;

    private int activityInstanceState;
}
