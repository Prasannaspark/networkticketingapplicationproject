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
@RequestMapping("/api/capable")
public class CapableITSupportController {
	private static final Logger loggers = LoggerFactory.getLogger(CapableITSupportController.class);

	@Autowired
	TicketService ticketService;

	@GetMapping("/urgentbutnotsevere")
	public ResponseEntity<List<Ticket>> getUrgentButNotCriticalTicket() throws TicketNotFoundException {
		List<Ticket> tP = ticketService.getUrgentButNotCriticalTickets();
		loggers.info("urgentbutnotsevere");
		return new ResponseEntity<>(tP, HttpStatus.OK);
	}

	@GetMapping("/noturgentbutcritical")
	public ResponseEntity<List<Ticket>> getNotUrgentButCriticalTicket() throws TicketNotFoundException {
		List<Ticket> tP = ticketService.getNotUrgentButCriticalTickets();
		List<Ticket> tS = ticketService.getTwentyFourPlusAgedTickets();
		List<Ticket> union = new ArrayList<>(tP);

		// Add all elements from tS to the union list
		union.addAll(tS);
		loggers.info("noturgentbutcritical");
		return new ResponseEntity<>(union, HttpStatus.OK);
	}
}
