package com.prodapt.networkticketingapplicationproject.service;

import java.util.Optional;

import com.prodapt.networkticketingapplicationproject.entities.ERole;
import com.prodapt.networkticketingapplicationproject.entities.Role;
import com.prodapt.networkticketingapplicationproject.exceptions.RoleNotFoundException;
public interface RoleService {
	
   public Optional<Role> findRoleByName(ERole role) throws RoleNotFoundException;
	
	public Optional<Role> findRoleById(Integer id) throws RoleNotFoundException;
	


}
