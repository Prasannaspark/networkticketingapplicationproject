package com.prodapt.networkticketingapplicationproject.requestentities;

import com.prodapt.networkticketingapplicationproject.entities.CustomerTier;
import com.prodapt.networkticketingapplicationproject.entities.IssueType;
import com.prodapt.networkticketingapplicationproject.entities.Priority;
import com.prodapt.networkticketingapplicationproject.entities.Severity;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class TicketUpdateRequest {
		
	private Long ticketId;
	
	private String title;
	
	private String description;
	
	@Enumerated(EnumType.STRING)
	private Priority priority;
	
	@Enumerated(EnumType.STRING)
	private Severity severity;
	
	@Enumerated(EnumType.STRING)
	private CustomerTier customerTier;
	
	@Enumerated(EnumType.STRING)
	private IssueType issueType;
	
	

}
