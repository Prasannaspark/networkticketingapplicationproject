package com.prodapt.networkticketingapplicationproject.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prodapt.networkticketingapplicationproject.entities.Ticket;
import com.prodapt.networkticketingapplicationproject.entities.UserEntity;
import com.prodapt.networkticketingapplicationproject.exceptions.TicketNotFoundException;
import com.prodapt.networkticketingapplicationproject.requestentities.TicketRequest;
import com.prodapt.networkticketingapplicationproject.requestentities.TicketUpdateRequest;
import com.prodapt.networkticketingapplicationproject.service.TicketService;
import com.prodapt.networkticketingapplicationproject.service.UserEntityService;

@RestController
@RequestMapping("/api/customer")

public class CustomerController {
	private static final Logger loggers = LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	UserEntityService userEntityService;
	
	@Autowired
	TicketService ticketService;
	
	@PostMapping("/addticket")
	public ResponseEntity<Ticket> addTicket(@RequestBody TicketRequest requestTicket){
		Ticket ticket=new Ticket();
		ticket.setTitle(requestTicket.getTitle());
		ticket.setCustomerTier(requestTicket.getCustomerTier());
		ticket.setDescription(requestTicket.getDescription());
		ticket.setIssueType(requestTicket.getIssueType());
		ticket.setPriority(requestTicket.getPriority());
		ticket.setSeverity(requestTicket.getSeverity());
		UserEntity user= userEntityService.getUserById(requestTicket.getUserEntityId());
		ticket.setUser(user);
		Ticket t= ticketService.addTicket(ticket);
		loggers.info("addticket");
		return new ResponseEntity<Ticket>(t,HttpStatus.OK);
	}
	
	@PostMapping("/updateticket")
	public ResponseEntity<Ticket> updateTicket(@RequestBody TicketUpdateRequest requestTicket) throws TicketNotFoundException{
		Ticket ticket=ticketService.getTicketById(requestTicket.getTicketId());
		ticket.setTitle(requestTicket.getTitle());
		ticket.setCustomerTier(requestTicket.getCustomerTier());
		ticket.setDescription(requestTicket.getDescription());
		ticket.setIssueType(requestTicket.getIssueType());
		ticket.setPriority(requestTicket.getPriority());
		ticket.setSeverity(requestTicket.getSeverity());
		Ticket t= ticketService.updateTicket(ticket);
		loggers.info("updateticket");
		return new ResponseEntity<Ticket>(t,HttpStatus.OK);
	}
	
	@PostMapping("/getticket")
	public ResponseEntity<Ticket> getTicket(@RequestParam("ticketId") Long ticketId) throws TicketNotFoundException{
		Ticket t= ticketService.getTicketById(ticketId);
		loggers.info("getticket");
		return new ResponseEntity<Ticket>(t,HttpStatus.OK);
	}
	
	@PostMapping("/getmytickets")
	public ResponseEntity<List<Ticket>> getMyTicket(@RequestParam("userEntityId") Long userEntityId) throws TicketNotFoundException{
		UserEntity user=userEntityService.getUserById(userEntityId);
		List<Ticket> t= ticketService.getByUserEntity(user);
		loggers.info("getmytickets");
		return new ResponseEntity<List<Ticket>>(t,HttpStatus.OK);
	}
}
