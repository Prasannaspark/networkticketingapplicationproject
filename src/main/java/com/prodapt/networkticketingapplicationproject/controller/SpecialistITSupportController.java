package com.prodapt.networkticketingapplicationproject.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prodapt.networkticketingapplicationproject.entities.Ticket;
import com.prodapt.networkticketingapplicationproject.exceptions.TicketNotFoundException;
import com.prodapt.networkticketingapplicationproject.service.TicketService;
@RestController
@RequestMapping("/api/specialist")
public class SpecialistITSupportController {
	private static final Logger loggers = LoggerFactory.getLogger(SpecialistITSupportController.class);

	@Autowired
	TicketService ticketService;

	@GetMapping("/critical")
	public ResponseEntity<List<Ticket>> getCriticalTicket() throws TicketNotFoundException {
		List<Ticket> tP = ticketService.getCriticalTickets();
		List<Ticket> tS = ticketService.getFourtyEightPlusAgedTickets();
		// Create a new list to hold the union of tP and tS
		List<Ticket> union = new ArrayList<>(tP);

		// Add all elements from tS to the union list
		union.addAll(tS);
		loggers.info("critical");
		return new ResponseEntity<>(union, HttpStatus.OK);

	}


}
