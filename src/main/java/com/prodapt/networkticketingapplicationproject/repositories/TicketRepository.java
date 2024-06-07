package com.prodapt.networkticketingapplicationproject.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.prodapt.networkticketingapplicationproject.entities.CustomerTier;
import com.prodapt.networkticketingapplicationproject.entities.Priority;
import com.prodapt.networkticketingapplicationproject.entities.Severity;
import com.prodapt.networkticketingapplicationproject.entities.Ticket;
import com.prodapt.networkticketingapplicationproject.entities.UserEntity;

@Repository
public interface TicketRepository extends CrudRepository<Ticket, Long>{
	
	public List<Ticket> findByPriority(Priority priority);
	
	public List<Ticket> findBySeverity(Severity severity);
	
	public List<Ticket> findByUser(UserEntity userEntity);
	
	public List<Ticket> findByCustomerTier(CustomerTier customerTier);

	public List<Ticket> findAll();
	
//	public List<Ticket> findByCreationDate(LocalDate creationDate);
//	
//	public List<Ticket> findByLastUpdated(LocalDate lastUpdated);

}
