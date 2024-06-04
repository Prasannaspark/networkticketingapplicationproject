package com.prodapt.networkticketingapplicationproject.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prodapt.networkticketingapplicationproject.service.UserEntityService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/networkticketingapp")

public class TestController {
	
	
	@Autowired
	UserEntityService userService;
	
	@GetMapping("/all")
	public String allAccess() {
		return "HELLO NETWORK TICKETING APPLICATION";
	}
	
	@GetMapping("/admin")
	public String adminAccess() {
		return "Admin Board.";
	}
	
	@GetMapping("/itsupport")
	public String userAccess() {
		return "IT Support.";
	}
 
	@GetMapping("/senioritsupport")
	public String parentAccess() {
		return "Senior IT Support.";
	}
}