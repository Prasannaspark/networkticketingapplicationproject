package com.prodapt.networkticketingapplicationproject.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prodapt.networkticketingapplicationproject.entities.Priority;
import com.prodapt.networkticketingapplicationproject.entities.Severity;
import com.prodapt.networkticketingapplicationproject.entities.Ticket;
import com.prodapt.networkticketingapplicationproject.service.TicketService;

@RestController
@RequestMapping("/api/senioritsupport")
public class SeniorITSupportController {
	@Autowired
	TicketService ticketService;
	
	@GetMapping("/critical")
	public ResponseEntity<List<Ticket>> getCriticalTicket(){
		List<Ticket> tP= ticketService.getByPriority(Priority.HIGH);
		List<Ticket> tS= ticketService.getBySeverity(Severity.HIGH);
		// Creating a new list to store the intersection
		List<Ticket> intersection = new ArrayList<>(tP); // Start with all elements from tP
		intersection.retainAll(tS); // Retain only elements that are present in tS as well
		return new ResponseEntity<List<Ticket>>(intersection,HttpStatus.OK);
	}
	
	@GetMapping("/urgentbutnotsevere")
	public ResponseEntity<List<Ticket>> getUrgentButNotCriticalTicket(){
		List<Ticket> tP= ticketService.getByPriority(Priority.HIGH);
		List<Ticket> tS= ticketService.getBySeverity(Severity.MEDIUM);
		// Creating a new list to store the intersection
		List<Ticket> intersection = new ArrayList<>(tP); // Start with all elements from tP
		intersection.retainAll(tS); // Retain only elements that are present in tS as well
		return new ResponseEntity<List<Ticket>>(intersection,HttpStatus.OK);
	}
	
	@GetMapping("/noturgentbutcritical")
	public ResponseEntity<List<Ticket>> getNotUrgentButCriticalTicket(){
		List<Ticket> tP= ticketService.getByPriority(Priority.LOW);
		List<Ticket> tS= ticketService.getBySeverity(Severity.HIGH);
		// Creating a new list to store the intersection
		List<Ticket> intersection = new ArrayList<>(tP); // Start with all elements from tP
		intersection.retainAll(tS); // Retain only elements that are present in tS as well
		return new ResponseEntity<List<Ticket>>(intersection,HttpStatus.OK);
	}
	
	
	

}
