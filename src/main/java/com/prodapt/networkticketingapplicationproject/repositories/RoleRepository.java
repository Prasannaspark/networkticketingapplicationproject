package com.prodapt.networkticketingapplicationproject.repositories;


import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.prodapt.networkticketingapplicationproject.entities.ERole;
import com.prodapt.networkticketingapplicationproject.entities.Role;



@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {

	  Optional<Role> findByName(ERole name);
	
}
