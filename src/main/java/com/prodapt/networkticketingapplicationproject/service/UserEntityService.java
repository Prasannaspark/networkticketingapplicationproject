package com.prodapt.networkticketingapplicationproject.service;

import java.util.Optional;

import com.prodapt.networkticketingapplicationproject.entities.ERole;
import com.prodapt.networkticketingapplicationproject.entities.Role;
import com.prodapt.networkticketingapplicationproject.entities.UserEntity;

public interface UserEntityService {
	
 public String updaterole(Long userid,Role role);
 
 public UserEntity getUserById(Long id);
 
 public Optional<UserEntity> findByUsername(String username);
	
 public Boolean existsByUsername(String username);

 public Boolean existsByEmail(String email);
 
 
 public Optional<UserEntity> findByRole(ERole role);
 
 public UserEntity addUserEntity(UserEntity user);
}
