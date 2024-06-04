package com.prodapt.networkticketingapplicationproject.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.prodapt.networkticketingapplicationproject.entities.CustomerTier;
import com.prodapt.networkticketingapplicationproject.entities.Priority;
import com.prodapt.networkticketingapplicationproject.entities.Severity;
import com.prodapt.networkticketingapplicationproject.entities.Status;
import com.prodapt.networkticketingapplicationproject.entities.Ticket;
import com.prodapt.networkticketingapplicationproject.entities.UserEntity;
import com.prodapt.networkticketingapplicationproject.repositories.TicketRepository;

@ExtendWith(MockitoExtension.class)
public class TicketServiceImplTest {

    @Mock
    private TicketRepository ticketRepository;

    @InjectMocks
    private TicketServiceImpl ticketService;

    private Ticket ticket;
    private UserEntity user;

    @BeforeEach
    void setUp() {
        user = new UserEntity();
        user.setId(1L);
        user.setUsername("testuser");

        ticket = new Ticket();
        ticket.setTicketId(1L);
        ticket.setUser(user);
        ticket.setPriority(Priority.MEDIUM);
        ticket.setSeverity(Severity.MEDIUM);
        ticket.setStatus(Status.OPEN);
        ticket.setCreationDate(LocalDate.now());
        ticket.setLastUpdated(LocalDate.now());
    }

    @Test
    void testAddTicket() {
        // Arrange
        when(ticketRepository.save(any(Ticket.class))).thenReturn(ticket);

        // Act
        Ticket savedTicket = ticketService.addTicket(ticket);

        // Assert
        assertNotNull(savedTicket);
        assertEquals(Status.OPEN, savedTicket.getStatus());
        assertEquals(ticket.getCreationDate(), savedTicket.getCreationDate());
        assertEquals(ticket.getLastUpdated(), savedTicket.getLastUpdated());
    }

    @Test
    void testGetTicketById_TicketExists() {
        // Arrange
        when(ticketRepository.findById(1L)).thenReturn(Optional.of(ticket));

        // Act
        Ticket foundTicket = ticketService.getTicketById(1L);

        // Assert
        assertNotNull(foundTicket);
        assertEquals(ticket, foundTicket);
    }

    @Test
    void testGetTicketById_TicketNotExists() {
        // Arrange
        when(ticketRepository.findById(1L)).thenReturn(Optional.empty());

        // Act
        Ticket foundTicket = ticketService.getTicketById(1L);

        // Assert
        assertNull(foundTicket);
    }

    @Test
    void testUpdateTicket_TicketExists() {
        // Arrange
        when(ticketRepository.findById(1L)).thenReturn(Optional.of(ticket));
        when(ticketRepository.save(any(Ticket.class))).thenReturn(ticket);

        // Act
        Ticket updatedTicket = ticketService.updateTicket(ticket);

        // Assert
        assertNotNull(updatedTicket);
        assertEquals(ticket, updatedTicket);
    }

    @Test
    void testUpdateTicket_TicketNotExists() {
        // Arrange
        when(ticketRepository.findById(1L)).thenReturn(Optional.empty());

        // Act
        Ticket updatedTicket = ticketService.updateTicket(ticket);

        // Assert
        assertNull(updatedTicket);
    }

    @Test
    void testGetByPriority() {
        // Arrange
        List<Ticket> ticketList = new ArrayList<>();
        ticketList.add(ticket);
        when(ticketRepository.findByPriority(Priority.MEDIUM)).thenReturn(ticketList);

        // Act
        List<Ticket> foundTickets = ticketService.getByPriority(Priority.MEDIUM);

        // Assert
        assertNotNull(foundTickets);
        assertEquals(1, foundTickets.size());
        assertEquals(ticket, foundTickets.get(0));
    }

    @Test
    void testGetBySeverity() {
        // Arrange
        List<Ticket> ticketList = new ArrayList<>();
        ticketList.add(ticket);
        when(ticketRepository.findBySeverity(Severity.MEDIUM)).thenReturn(ticketList);

        // Act
        List<Ticket> foundTickets = ticketService.getBySeverity(Severity.MEDIUM);

        // Assert
        assertNotNull(foundTickets);
        assertEquals(1, foundTickets.size());
        assertEquals(ticket, foundTickets.get(0));
    }

    @Test
    void testGetByUserEntity() {
        // Arrange
        List<Ticket> ticketList = new ArrayList<>();
        ticketList.add(ticket);
        when(ticketRepository.findByUser(user)).thenReturn(ticketList);

        // Act
        List<Ticket> foundTickets = ticketService.getByUserEntity(user);

        // Assert
        assertNotNull(foundTickets);
        assertEquals(1, foundTickets.size());
        assertEquals(ticket, foundTickets.get(0));
    }

    @Test
    void testGetAllTickets() {
        // Arrange
        List<Ticket> ticketList = new ArrayList<>();
        ticketList.add(ticket);
        when(ticketRepository.findAll()).thenReturn(ticketList);

        // Act
        List<Ticket> foundTickets = ticketService.getAllTickets();

        // Assert
        assertNotNull(foundTickets);
        assertEquals(1, foundTickets.size());
        assertEquals(ticket, foundTickets.get(0));
    }

    @Test
    void testGetRoutineTickets() {
        // Arrange
        List<Ticket> mediumPriorityTickets = new ArrayList<>();
        mediumPriorityTickets.add(ticket);

        List<Ticket> goldTierTickets = new ArrayList<>();
        goldTierTickets.add(ticket);

        List<Ticket> lowPriorityTickets = new ArrayList<>();
        when(ticketRepository.findByPriority(Priority.MEDIUM)).thenReturn(mediumPriorityTickets);
        when(ticketRepository.findByCustomerTier(CustomerTier.GOLD)).thenReturn(goldTierTickets);
        when(ticketRepository.findByPriority(Priority.LOW)).thenReturn(lowPriorityTickets);

        // Act
        List<Ticket> routineTickets = ticketService.getRoutineTickets();

        // Assert
        assertNotNull(routineTickets);
        assertEquals(1, routineTickets.size());
        assertTrue(routineTickets.contains(ticket));
    }

    @Test
    void testGetMinorTickets() {
        // Arrange
        List<Ticket> lowPriorityTickets = new ArrayList<>();
        List<Ticket> lowSeverityTickets = new ArrayList<>();
        lowPriorityTickets.add(ticket);
        lowSeverityTickets.add(ticket);
        when(ticketRepository.findByPriority(Priority.LOW)).thenReturn(lowPriorityTickets);
        when(ticketRepository.findBySeverity(Severity.LOW)).thenReturn(lowSeverityTickets);

        // Act
        List<Ticket> minorTickets = ticketService.getMinorTickets();

        // Assert
        assertNotNull(minorTickets);
        assertEquals(1, minorTickets.size());
        assertTrue(minorTickets.contains(ticket));
    }

    @Test
    void testGetCriticalTickets() {
        // Arrange
        List<Ticket> highPriorityTickets = new ArrayList<>();
        List<Ticket> highSeverityTickets = new ArrayList<>();
        highPriorityTickets.add(ticket);
        highSeverityTickets.add(ticket);
        when(ticketRepository.findByPriority(Priority.HIGH)).thenReturn(highPriorityTickets);
        when(ticketRepository.findBySeverity(Severity.HIGH)).thenReturn(highSeverityTickets);

        // Act
        List<Ticket> criticalTickets = ticketService.getCriticalTickets();

        // Assert
        assertNotNull(criticalTickets);
        assertEquals(1, criticalTickets.size());
        assertTrue(criticalTickets.contains(ticket));
    }

    @Test
    void testGetUrgentButNotCriticalTickets() {
        // Arrange
        List<Ticket> highPriorityTickets = new ArrayList<>();
        List<Ticket> mediumSeverityTickets = new ArrayList<>();
        highPriorityTickets.add(ticket);
        mediumSeverityTickets.add(ticket);
        when(ticketRepository.findByPriority(Priority.HIGH)).thenReturn(highPriorityTickets);
        when(ticketRepository.findBySeverity(Severity.MEDIUM)).thenReturn(mediumSeverityTickets);

        // Act
        List<Ticket> urgentButNotCriticalTickets = ticketService.getUrgentButNotCriticalTickets();

        // Assert
        assertNotNull(urgentButNotCriticalTickets);
        assertEquals(1, urgentButNotCriticalTickets.size());
        assertTrue(urgentButNotCriticalTickets.contains(ticket));
    }

    @Test
    void testGetNotUrgentButCriticalTickets() {
        // Arrange
        List<Ticket> lowPriorityTickets = new ArrayList<>();
        List<Ticket> highSeverityTickets = new ArrayList<>();
        lowPriorityTickets.add(ticket);
        highSeverityTickets.add(ticket);
        when(ticketRepository.findByPriority(Priority.LOW)).thenReturn(lowPriorityTickets);
        when(ticketRepository.findBySeverity(Severity.HIGH)).thenReturn(highSeverityTickets);

        // Act
        List<Ticket> notUrgentButCriticalTickets = ticketService.getNotUrgentButCriticalTickets();

        // Assert
        assertNotNull(notUrgentButCriticalTickets);
        assertEquals(1, notUrgentButCriticalTickets.size());
        assertTrue(notUrgentButCriticalTickets.contains(ticket));
    }
}
