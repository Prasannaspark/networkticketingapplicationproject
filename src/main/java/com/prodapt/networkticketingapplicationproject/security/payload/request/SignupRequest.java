package com.prodapt.networkticketingapplicationproject.security.payload.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class SignupRequest {

	private String username;

	private String email;
	
	private String password;


	

}
