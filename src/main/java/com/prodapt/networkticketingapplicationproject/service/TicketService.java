package com.prodapt.networkticketingapplicationproject.service;

import java.util.List;

import com.prodapt.networkticketingapplicationproject.entities.Priority;
import com.prodapt.networkticketingapplicationproject.entities.Severity;
import com.prodapt.networkticketingapplicationproject.entities.Ticket;
import com.prodapt.networkticketingapplicationproject.entities.UserEntity;
import com.prodapt.networkticketingapplicationproject.exceptions.TicketNotFoundException;

public interface TicketService {

	public Ticket addTicket(Ticket ticket);

	public Ticket getTicketById(Long ticketId) throws TicketNotFoundException;

	public Ticket updateTicket(Ticket ticket) throws TicketNotFoundException;

	public List<Ticket> getByPriority(Priority priority) throws TicketNotFoundException;

	public List<Ticket> getBySeverity(Severity severity) throws TicketNotFoundException;

	public List<Ticket> getByUserEntity(UserEntity userEntity) throws TicketNotFoundException;

	public List<Ticket> getAllTickets() throws TicketNotFoundException;

	public List<Ticket> getRoutineTickets() throws TicketNotFoundException;

	public List<Ticket> getMinorTickets() throws TicketNotFoundException;

	public List<Ticket> getCriticalTickets() throws TicketNotFoundException;

	public List<Ticket> getUrgentButNotCriticalTickets() throws TicketNotFoundException;

	public List<Ticket> getNotUrgentButCriticalTickets() throws TicketNotFoundException;

	public List<Ticket> getFourtyEightPlusAgedTickets() throws TicketNotFoundException;

	public List<Ticket> getTwentyFourPlusAgedTickets() throws TicketNotFoundException;

}
