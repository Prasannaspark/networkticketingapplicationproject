package com.prodapt.networkticketingapplicationproject.service;

import java.util.Optional;

import com.prodapt.networkticketingapplicationproject.entities.ERole;
import com.prodapt.networkticketingapplicationproject.entities.Role;
public interface RoleService {
	
   public Optional<Role> findRoleByName(ERole role);
	
	public Optional<Role> findRoleById(Integer id);
	


}
