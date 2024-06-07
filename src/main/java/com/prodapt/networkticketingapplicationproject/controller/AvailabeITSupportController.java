package com.prodapt.networkticketingapplicationproject.controller;

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
@RequestMapping("/api/available")
public class AvailabeITSupportController {
	private static final Logger loggers = LoggerFactory.getLogger(AvailabeITSupportController.class);

	@Autowired
	TicketService ticketService;
	
	@GetMapping("/routine")
	public ResponseEntity<List<Ticket>> getRoutineTicket() throws TicketNotFoundException{
		List<Ticket> t= ticketService.getRoutineTickets();
		loggers.info("routine");
		return new ResponseEntity<>(t,HttpStatus.OK);
	}
	
	@GetMapping("/minor")
	public ResponseEntity<List<Ticket>> getMinorTicket() throws TicketNotFoundException{
		List<Ticket> t =ticketService.getMinorTickets();
		loggers.info("minor");
		return new ResponseEntity<>(t,HttpStatus.OK);
	}

}
