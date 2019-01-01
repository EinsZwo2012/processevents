package de.mhens.events.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class HistoricJobInstanceDto extends EventDto{
 
	private String id;
     
	private String activityId;
	
	private Integer state;

	private String processInstanceId;

    private Date jobDueDate;
}
