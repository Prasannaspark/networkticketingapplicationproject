package com.prodapt.networkticketingapplicationproject.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prodapt.networkticketingapplicationproject.entities.ERole;
import com.prodapt.networkticketingapplicationproject.entities.Role;
import com.prodapt.networkticketingapplicationproject.entities.Ticket;
import com.prodapt.networkticketingapplicationproject.exceptions.RoleNotFoundException;
import com.prodapt.networkticketingapplicationproject.exceptions.TicketNotFoundException;
import com.prodapt.networkticketingapplicationproject.security.jwt.AuthTokenFilter;
import com.prodapt.networkticketingapplicationproject.service.RoleService;
import com.prodapt.networkticketingapplicationproject.service.TicketService;
import com.prodapt.networkticketingapplicationproject.service.UserEntityService;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/admin")
public class AdminController {
	private static final Logger loggers = LoggerFactory.getLogger(AdminController.class);
	@Autowired
	private UserEntityService userService;

	@Autowired
	private RoleService roleService;
	
	@Autowired
	private TicketService ticketService;

	@PostMapping("/updateuserrole")
	public ResponseEntity<String> updateUserRole(@RequestParam("id") Long userId, @RequestParam("role") ERole role) throws RoleNotFoundException {
		Optional<Role> r = roleService.findRoleByName(role);
		String message = userService.updaterole(userId, r.get());
		loggers.info("updateuserrole");
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}
	
	@GetMapping("/getall")
	public ResponseEntity<List<Ticket>> getAll() throws TicketNotFoundException {
		List<Ticket> tickets= ticketService.getAllTickets();
		loggers.info("getall");
		return new ResponseEntity<List<Ticket>>(tickets,HttpStatus.OK);
	}
	

}