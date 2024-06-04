package com.prodapt.networkticketingapplicationproject.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prodapt.networkticketingapplicationproject.entities.ERole;
import com.prodapt.networkticketingapplicationproject.entities.Role;
import com.prodapt.networkticketingapplicationproject.entities.UserEntity;
import com.prodapt.networkticketingapplicationproject.repositories.UserRepository;

@Service
public class UserEntityServiceImpl  implements UserEntityService{

	@Autowired
	UserRepository repo;
	@Override
	public String updaterole(Long userid, Role role) {
		Optional<UserEntity> user= repo.findById(userid);
		if(user.isPresent())
		{
		  user.get().setRole(role);
		  repo.save(user.get());
		  return "Role Updated Successfully!!!";
		  
		}
		return null;
	}

	@Override
	public UserEntity getUserById(Long id) {
		Optional<UserEntity> user= repo.findById(id);
		if(user.isPresent())
		{
			return user.get();
		}
		return null;
		
	}

	@Override
	public Optional<UserEntity> findByUsername(String username) {
		Optional<UserEntity> user= repo.findByUsername(username);
		if(user.isPresent())
		{
			return user;
		}
		return null;
	}

	@Override
	public Boolean existsByUsername(String username) {
		boolean b=repo.existsByUsername(username);
		return b;
	}

	@Override
	public Boolean existsByEmail(String email) {
		boolean b=repo.existsByEmail(email);
		return b;
	}

	@Override
	public Optional<UserEntity> findByRole(ERole role) {
		Optional<UserEntity> user= repo.findByRole(role);
		if(user.isPresent())
		{
			return user;
		}
		return null;
	}
	
	public UserEntity addUserEntity(UserEntity user) {
		UserEntity userEntity= repo.save(user);
		return userEntity;
	}

}
