package com.prodapt.networkticketingapplicationproject.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prodapt.networkticketingapplicationproject.entities.ERole;
import com.prodapt.networkticketingapplicationproject.repositories.RoleRepository;
import com.prodapt.networkticketingapplicationproject.entities.Role;
import com.prodapt.networkticketingapplicationproject.exceptionmessages.QueryMapper;
import com.prodapt.networkticketingapplicationproject.exceptions.RoleNotFoundException;
@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleRepository repo;
	@Override
	public Optional<Role> findRoleByName(ERole role)  throws RoleNotFoundException {
		Optional<Role> r= repo.findByName(role);
		if(r.isPresent()) return r;
		else throw new RoleNotFoundException(QueryMapper.ROLENOTFOUND);
	}
	@Override
	public Optional<Role> findRoleById(Integer id)  throws RoleNotFoundException{
		Optional<Role> role= repo.findById(id);
		if(role.isPresent()) return role;
		else throw new RoleNotFoundException(QueryMapper.ROLENOTFOUND);
	}
	
	
	

}