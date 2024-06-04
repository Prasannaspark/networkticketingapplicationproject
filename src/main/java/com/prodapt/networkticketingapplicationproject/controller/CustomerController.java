package com.prodapt.networkticketingapplicationproject.controller;

import java.util.List;

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
import com.prodapt.networkticketingapplicationproject.requestentities.TicketRequest;
import com.prodapt.networkticketingapplicationproject.requestentities.TicketUpdateRequest;
import com.prodapt.networkticketingapplicationproject.service.TicketService;
import com.prodapt.networkticketingapplicationproject.service.UserEntityService;

@RestController
@RequestMapping("/api/customer")

public class CustomerController {
	
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
		return new ResponseEntity<Ticket>(t,HttpStatus.OK);
	}
	
	@PostMapping("/updateticket")
	public ResponseEntity<Ticket> updateTicket(@RequestBody TicketUpdateRequest requestTicket){
		Ticket ticket=ticketService.getTicketById(requestTicket.getTicketId());
		ticket.setTitle(requestTicket.getTitle());
		ticket.setCustomerTier(requestTicket.getCustomerTier());
		ticket.setDescription(requestTicket.getDescription());
		ticket.setIssueType(requestTicket.getIssueType());
		ticket.setPriority(requestTicket.getPriority());
		ticket.setSeverity(requestTicket.getSeverity());
		Ticket t= ticketService.updateTicket(ticket);
		return new ResponseEntity<Ticket>(t,HttpStatus.OK);
	}
	
	@PostMapping("/getticket")
	public ResponseEntity<Ticket> getTicket(@RequestParam("ticketId") Long ticketId){
		Ticket t= ticketService.getTicketById(ticketId);
		return new ResponseEntity<Ticket>(t,HttpStatus.OK);
	}
	
	@PostMapping("/getmytickets")
	public ResponseEntity<List<Ticket>> getMyTicket(@RequestParam("userEntityId") Long userEntityId){
		UserEntity user=userEntityService.getUserById(userEntityId);
		List<Ticket> t= ticketService.getByUserEntity(user);
		return new ResponseEntity<List<Ticket>>(t,HttpStatus.OK);
	}
}