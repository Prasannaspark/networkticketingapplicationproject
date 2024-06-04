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
@RequestMapping("/api/itsupport")

public class ITSupportController {
	
	@Autowired
	TicketService ticketService;
	
	@GetMapping("/routine")
	public ResponseEntity<List<Ticket>> getRoutineTicket(){
		List<Ticket> t= ticketService.getByPriority(Priority.MEDIUM);
		return new ResponseEntity<List<Ticket>>(t,HttpStatus.OK);
	}
	
	@GetMapping("/minor")
	public ResponseEntity<List<Ticket>> getMinorTicket(){
		List<Ticket> tP= ticketService.getByPriority(Priority.LOW);
		List<Ticket> tS= ticketService.getBySeverity(Severity.LOW);
		// Creating a new list to store the intersection
		List<Ticket> intersection = new ArrayList<>(tP); // Start with all elements from tP
		intersection.retainAll(tS); // Retain only elements that are present in tS as well
		return new ResponseEntity<List<Ticket>>(intersection,HttpStatus.OK);
	}

}
