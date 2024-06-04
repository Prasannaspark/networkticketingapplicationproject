package com.prodapt.networkticketingapplicationproject.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.prodapt.networkticketingapplicationproject.entities.CustomerTier;
import com.prodapt.networkticketingapplicationproject.entities.IssueType;
import com.prodapt.networkticketingapplicationproject.entities.Priority;
import com.prodapt.networkticketingapplicationproject.entities.Severity;
import com.prodapt.networkticketingapplicationproject.entities.Ticket;
import com.prodapt.networkticketingapplicationproject.entities.UserEntity;
import com.prodapt.networkticketingapplicationproject.requestentities.TicketRequest;
import com.prodapt.networkticketingapplicationproject.requestentities.TicketUpdateRequest;
import com.prodapt.networkticketingapplicationproject.service.TicketService;
import com.prodapt.networkticketingapplicationproject.service.UserEntityService;

@ExtendWith(MockitoExtension.class)
public class CustomerControllerTest {

    @Mock
    private UserEntityService userEntityService;

    @Mock
    private TicketService ticketService;

    @InjectMocks
    private CustomerController customerController;

    private TicketRequest ticketRequest;
    private TicketUpdateRequest ticketUpdateRequest;
    private Ticket ticket;
    private UserEntity user;
    private List<Ticket> tickets;

    @BeforeEach
    void setUp() {
        ticketRequest = new TicketRequest();
        ticketRequest.setTitle("Test Ticket");
        ticketRequest.setCustomerTier(CustomerTier.GOLD);
        ticketRequest.setDescription("Test Description");
        ticketRequest.setIssueType(IssueType.PERFORMANCE);
        ticketRequest.setPriority(Priority.HIGH);
        ticketRequest.setSeverity(Severity.HIGH);
        ticketRequest.setUserEntityId(1L);

        ticketUpdateRequest = new TicketUpdateRequest();
        ticketUpdateRequest.setTicketId(1L);
        ticketUpdateRequest.setTitle("Updated Ticket");
        ticketUpdateRequest.setCustomerTier(CustomerTier.SILVER);
        ticketUpdateRequest.setDescription("Updated Description");
        ticketUpdateRequest.setIssueType(IssueType.PERFORMANCE);
        ticketUpdateRequest.setPriority(Priority.LOW);
        ticketUpdateRequest.setSeverity(Severity.HIGH);

        ticket = new Ticket();
        ticket.setTicketId(1L);

        user = new UserEntity();
        user.setId(1L);

        tickets = Arrays.asList(ticket);
    }

    @Test
    void testAddTicket() {
        // Arrange
        when(userEntityService.getUserById(1L)).thenReturn(user);
        when(ticketService.addTicket(any(Ticket.class))).thenReturn(ticket);

        // Act
        ResponseEntity<Ticket> response = customerController.addTicket(ticketRequest);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ticket, response.getBody());
    }

    @Test
    void testUpdateTicket() {
        // Arrange
        when(ticketService.getTicketById(1L)).thenReturn(ticket);
        when(ticketService.updateTicket(any(Ticket.class))).thenReturn(ticket);

        // Act
        ResponseEntity<Ticket> response = customerController.updateTicket(ticketUpdateRequest);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ticket, response.getBody());
    }

    @Test
    void testGetTicket() {
        // Arrange
        when(ticketService.getTicketById(1L)).thenReturn(ticket);

        // Act
        ResponseEntity<Ticket> response = customerController.getTicket(1L);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ticket, response.getBody());
    }

    @Test
    void testGetMyTicket() {
        // Arrange
        when(userEntityService.getUserById(1L)).thenReturn(user);
        when(ticketService.getByUserEntity(user)).thenReturn(tickets);

        // Act
        ResponseEntity<List<Ticket>> response = customerController.getMyTicket(1L);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(tickets, response.getBody());
    }
}
