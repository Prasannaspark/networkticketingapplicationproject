package com.prodapt.networkticketingapplicationproject.controller;




import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prodapt.networkticketingapplicationproject.entities.UserEntity;
import com.prodapt.networkticketingapplicationproject.security.jwt.JwtUtils;
import com.prodapt.networkticketingapplicationproject.security.payload.request.LoginRequest;
import com.prodapt.networkticketingapplicationproject.security.payload.request.SignupRequest;
import com.prodapt.networkticketingapplicationproject.security.payload.response.JwtResponse;
import com.prodapt.networkticketingapplicationproject.security.payload.response.MessageResponse;
import com.prodapt.networkticketingapplicationproject.security.service.UserDetailsImpl;
import com.prodapt.networkticketingapplicationproject.service.RoleService;
import com.prodapt.networkticketingapplicationproject.service.UserEntityService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

	private static final Logger loggers = LoggerFactory.getLogger(AuthController.class);
	
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;
	
	@Autowired
	UserEntityService userEntityService;
	
	@Autowired
	RoleService roleService;
	
	

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		loggers.info("User Signin");
		
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		String role = userDetails.getAuthorities().stream().findFirst() // Get the first authority
				.map(item -> item.getAuthority()) // Map it to its authority string
				.orElse(null);

		return ResponseEntity.ok(
				new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), role));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		loggers.info("User Signin");
		if (userEntityService.existsByUsername(signUpRequest.getUsername())) {
			loggers.info("User Name Already Exist");
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
	
		}

		if (userEntityService.existsByEmail(signUpRequest.getEmail())) {
			loggers.info("User Email Already Exist");
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
		}
		

		// Create new user's account
		UserEntity user = new UserEntity(signUpRequest.getUsername(), signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()));
		
        user.setRole(roleService.findRoleById(4).get());
        userEntityService.addUserEntity(user);
  
        
		loggers.info("User Registered Exist");
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}

	
}