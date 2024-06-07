package com.prodapt.networkticketingapplicationproject.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prodapt.networkticketingapplicationproject.entities.CustomerTier;
import com.prodapt.networkticketingapplicationproject.entities.Priority;
import com.prodapt.networkticketingapplicationproject.entities.Severity;
import com.prodapt.networkticketingapplicationproject.entities.Status;
import com.prodapt.networkticketingapplicationproject.entities.Ticket;
import com.prodapt.networkticketingapplicationproject.entities.UserEntity;
import com.prodapt.networkticketingapplicationproject.exceptionmessages.QueryMapper;
import com.prodapt.networkticketingapplicationproject.exceptions.TicketNotFoundException;
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
	public Ticket getTicketById(Long ticketId) throws TicketNotFoundException{
		Optional<Ticket> t = repo.findById(ticketId);
		if (t.isPresent()) {
			return t.get();
		}
		else throw new TicketNotFoundException(QueryMapper.TICKETNOTFOUND);
	}

	@Override
	public Ticket updateTicket(Ticket ticket) throws TicketNotFoundException {
		Optional<Ticket> t = repo.findById(ticket.getTicketId());
		if (t.isPresent()) {
			return repo.save(t.get());
		}
		else throw new TicketNotFoundException(QueryMapper.TICKETNOTFOUND);
	}

	@Override
	public List<Ticket> getByPriority(Priority priority) throws TicketNotFoundException{
		List<Ticket> ticketList = repo.findByPriority(priority);
		return ticketList;
	}

	@Override
	public List<Ticket> getBySeverity(Severity severity) throws TicketNotFoundException{
		List<Ticket> ticketList = repo.findBySeverity(severity);
		if(!ticketList.isEmpty()) return ticketList;
		else throw new TicketNotFoundException(QueryMapper.TICKETNOTFOUND);
	}

	@Override
	public List<Ticket> getByUserEntity(UserEntity userEntity) throws TicketNotFoundException{
		List<Ticket> ticketList = repo.findByUser(userEntity);
		if(!ticketList.isEmpty()) return ticketList;
		else throw new TicketNotFoundException(QueryMapper.TICKETNOTFOUND);
	}

	@Override
	public List<Ticket> getAllTickets() throws TicketNotFoundException{
		List<Ticket> ticketList = repo.findAll();
		if(!ticketList.isEmpty()) return ticketList;
		else throw new TicketNotFoundException(QueryMapper.TICKETNOTFOUND);
	}

	@Override
	public List<Ticket> getRoutineTickets() throws TicketNotFoundException{
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
		if(!unionList.isEmpty()) return unionList;
		else throw new TicketNotFoundException(QueryMapper.TICKETNOTFOUND);
	}

	@Override
	public List<Ticket> getMinorTickets() throws TicketNotFoundException {
		List<Ticket> tP = repo.findByPriority(Priority.LOW);
		List<Ticket> tS = repo.findBySeverity(Severity.LOW);
		List<Ticket> intersection = new ArrayList<>(tP); // Start with all elements from tP
		intersection.retainAll(tS);
		if(!intersection.isEmpty()) return intersection;
		else throw new TicketNotFoundException(QueryMapper.TICKETNOTFOUND);
	}

	@Override
	public List<Ticket> getCriticalTickets() throws TicketNotFoundException{
		List<Ticket> tP = repo.findByPriority(Priority.HIGH);
		List<Ticket> tS = repo.findBySeverity(Severity.HIGH);
		List<Ticket> intersection = new ArrayList<Ticket>(tP);
		intersection.retainAll(tS);

		List<Ticket> tCT = repo.findByCustomerTier(CustomerTier.GOLD);
		List<Ticket> tLP = repo.findByPriority(Priority.MEDIUM);
		List<Ticket> tCTLP = new ArrayList<>(tLP);
		tCTLP.retainAll(tCT);
		List<Ticket> union = new ArrayList<>(intersection);
		union.addAll(tCTLP);
		if(!union.isEmpty()) return union;
		else throw new TicketNotFoundException(QueryMapper.TICKETNOTFOUND);

	}

	@Override
	public List<Ticket> getUrgentButNotCriticalTickets() throws TicketNotFoundException{
		List<Ticket> tP = repo.findByPriority(Priority.HIGH);
		List<Ticket> tS = repo.findBySeverity(Severity.MEDIUM);

		List<Ticket> intersection = new ArrayList<>(tP); // Start with all elements from tP
		intersection.retainAll(tS);

		List<Ticket> tCT = repo.findByCustomerTier(CustomerTier.GOLD);
		List<Ticket> tLP = repo.findByPriority(Priority.MEDIUM);
		List<Ticket> tCTLP = new ArrayList<>(tLP);
		tCTLP.retainAll(tCT);
		List<Ticket> union = new ArrayList<>(intersection);
		union.addAll(tCTLP);
		if(!union.isEmpty()) return union;
		else throw new TicketNotFoundException(QueryMapper.TICKETNOTFOUND);
	}

	@Override
	public List<Ticket> getNotUrgentButCriticalTickets() throws TicketNotFoundException{
		List<Ticket> tP = repo.findByPriority(Priority.LOW);
		List<Ticket> tS = repo.findBySeverity(Severity.HIGH);
		List<Ticket> intersection = new ArrayList<>(tP); // Start with all elements from tP
		intersection.retainAll(tS);
		if(!intersection.isEmpty()) return intersection;
		else throw new TicketNotFoundException(QueryMapper.TICKETNOTFOUND);

	}

	@Override
	public List<Ticket> getFourtyEightPlusAgedTickets() throws TicketNotFoundException{
		LocalDate currentDate = LocalDate.now();
		LocalDate fortyEightHoursAgo = currentDate.minusDays(2);

		// Assuming there's a method to retrieve all tickets
		List<Ticket> allTickets = repo.findAll();

		// Filter tickets older than forty-eight hours
		List<Ticket> agedTickets = allTickets.stream()
				.filter(ticket -> ticket.getCreationDate().isBefore(fortyEightHoursAgo)).collect(Collectors.toList());

		if(!agedTickets.isEmpty()) return agedTickets;
		else throw new TicketNotFoundException(QueryMapper.TICKETNOTFOUND);
	}

	@Override
	public List<Ticket> getTwentyFourPlusAgedTickets() throws TicketNotFoundException{
		LocalDate currentDate = LocalDate.now();
		LocalDate twentyFourHoursAgo = currentDate.minusDays(1);

		// Assuming there's a method to retrieve all tickets
		List<Ticket> allTickets = getAllTickets();

		// Filter tickets older than forty-eight hours
		List<Ticket> agedTickets = allTickets.stream()
				.filter(ticket -> ticket.getCreationDate().isBefore(twentyFourHoursAgo)).collect(Collectors.toList());

		if(!agedTickets.isEmpty()) return agedTickets;
		else throw new TicketNotFoundException(QueryMapper.TICKETNOTFOUND);
	}

}
