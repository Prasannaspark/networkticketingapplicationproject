package com.prodapt.networkticketingapplicationproject.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prodapt.networkticketingapplicationproject.entities.CustomerTier;
import com.prodapt.networkticketingapplicationproject.entities.Priority;
import com.prodapt.networkticketingapplicationproject.entities.Severity;
import com.prodapt.networkticketingapplicationproject.entities.Status;
import com.prodapt.networkticketingapplicationproject.entities.Ticket;
import com.prodapt.networkticketingapplicationproject.entities.UserEntity;
import com.prodapt.networkticketingapplicationproject.repositories.TicketRepository;

@Service
public class TicketServiceImpl implements TicketService {

	@Autowired
	TicketRepository repo;

	@Override
	public Ticket addTicket(Ticket ticket) {
		ticket.setStatus(Status.OPEN);
		ticket.setCreationDate(LocalDate.now());
		ticket.setLastUpdated(LocalDate.now());
		Ticket t = repo.save(ticket);
		return t;
	}

	@Override
	public Ticket getTicketById(Long ticketId) {
		Optional<Ticket> t = repo.findById(ticketId);
		if (t.isPresent()) {
			return t.get();
		}
		return null;
	}

	@Override
	public Ticket updateTicket(Ticket ticket) {
		Optional<Ticket> t = repo.findById(ticket.getTicketId());
		if (t.isPresent()) {
			return repo.save(t.get());
		}
		return null;
	}

	@Override
	public List<Ticket> getByPriority(Priority priority) {
		List<Ticket> ticketList = repo.findByPriority(priority);
		return ticketList;
	}

	@Override
	public List<Ticket> getBySeverity(Severity severity) {
		List<Ticket> ticketList = repo.findBySeverity(severity);
		return ticketList;
	}

	@Override
	public List<Ticket> getByUserEntity(UserEntity userEntity) {
		List<Ticket> ticketList = repo.findByUser(userEntity);
		return ticketList;
	}

	@Override
	public List<Ticket> getAllTickets() {
		List<Ticket> ticketList = repo.findAll();
		return ticketList;
	}

	@Override
	public List<Ticket> getRoutineTickets() {
		List<Ticket> tMP = repo.findByPriority(Priority.MEDIUM);
		List<Ticket> tCT = repo.findByCustomerTier(CustomerTier.GOLD);
		List<Ticket> tLP = repo.findByPriority(Priority.LOW);
		List<Ticket> tCTLP = new ArrayList<>(tLP);
		tCTLP.retainAll(tCT);

		List<Ticket> unionList = new ArrayList<>(tMP);

		for (Ticket ticket : tCTLP) {
			if (!unionList.contains(ticket)) {
				unionList.add(ticket);
			}
		}
		return unionList;
	}

	@Override
	public List<Ticket> getMinorTickets() {
		List<Ticket> tP = repo.findByPriority(Priority.LOW);
		List<Ticket> tS = repo.findBySeverity(Severity.LOW);
		List<Ticket> intersection = new ArrayList<>(tP); // Start with all elements from tP
		intersection.retainAll(tS);
		return intersection;
	}

	@Override
	public List<Ticket> getCriticalTickets() {
		List<Ticket> tP = repo.findByPriority(Priority.HIGH);
		List<Ticket> tS = repo.findBySeverity(Severity.HIGH);
		List<Ticket> intersection = new ArrayList<>(tP); // Start with all elements from tP
		intersection.retainAll(tS);
		return intersection;
		
		
	}

	@Override
	public List<Ticket> getUrgentButNotCriticalTickets() {
		List<Ticket> tP = repo.findByPriority(Priority.HIGH);
		List<Ticket> tS = repo.findBySeverity(Severity.MEDIUM);
		List<Ticket> intersection = new ArrayList<>(tP); // Start with all elements from tP
		intersection.retainAll(tS);
		return intersection;
	}

	@Override
	public List<Ticket> getNotUrgentButCriticalTickets() {
		List<Ticket> tP = repo.findByPriority(Priority.LOW);
		List<Ticket> tS = repo.findBySeverity(Severity.HIGH);
		List<Ticket> intersection = new ArrayList<>(tP); // Start with all elements from tP
		intersection.retainAll(tS);
		return intersection;

	}
	
	

}
