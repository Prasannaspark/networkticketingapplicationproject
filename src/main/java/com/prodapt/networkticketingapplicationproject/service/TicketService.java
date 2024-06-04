package com.prodapt.networkticketingapplicationproject.service;

import java.util.List;

import com.prodapt.networkticketingapplicationproject.entities.Priority;
import com.prodapt.networkticketingapplicationproject.entities.Severity;
import com.prodapt.networkticketingapplicationproject.entities.Ticket;
import com.prodapt.networkticketingapplicationproject.entities.UserEntity;

public interface TicketService {

	public Ticket addTicket(Ticket ticket);

	public Ticket getTicketById(Long ticketId);

	public Ticket updateTicket(Ticket ticket);

	public List<Ticket> getByPriority(Priority priority);

	public List<Ticket> getBySeverity(Severity severity);

	public List<Ticket> getByUserEntity(UserEntity userEntity);
	
	public List<Ticket> getAllTickets();
	
	public List<Ticket> getRoutineTickets();
	public List<Ticket> getMinorTickets();
	public List<Ticket> getCriticalTickets();
	public List<Ticket> getUrgentButNotCriticalTickets();
	public List<Ticket> getNotUrgentButCriticalTickets();

}
