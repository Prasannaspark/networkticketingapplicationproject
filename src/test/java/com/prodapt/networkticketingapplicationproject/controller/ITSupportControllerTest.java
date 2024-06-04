package com.prodapt.networkticketingapplicationproject.controller;

import static org.junit.jupiter.api.Assertions.*;
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

import com.prodapt.networkticketingapplicationproject.entities.Priority;
import com.prodapt.networkticketingapplicationproject.entities.Severity;
import com.prodapt.networkticketingapplicationproject.entities.Ticket;
import com.prodapt.networkticketingapplicationproject.service.TicketService;

@ExtendWith(MockitoExtension.class)
public class ITSupportControllerTest {

    @Mock
    private TicketService ticketService;

    @InjectMocks
    private ITSupportController itSupportController;

    private Ticket mediumPriorityTicket;
    private Ticket lowPriorityTicket;
    private Ticket lowSeverityTicket;
    @SuppressWarnings("unused")
	private List<Ticket> tickets;

    @BeforeEach
    void setUp() {
        mediumPriorityTicket = new Ticket();
        mediumPriorityTicket.setTicketId(1L);
        mediumPriorityTicket.setPriority(Priority.MEDIUM);

        lowPriorityTicket = new Ticket();
        lowPriorityTicket.setTicketId(2L);
        lowPriorityTicket.setPriority(Priority.LOW);

        lowSeverityTicket = new Ticket();
        lowSeverityTicket.setTicketId(3L);
        lowSeverityTicket.setSeverity(Severity.LOW);

        tickets = Arrays.asList(mediumPriorityTicket, lowPriorityTicket, lowSeverityTicket);
    }

    @Test
    void testGetRoutineTicket() {
        // Arrange
        when(ticketService.getByPriority(Priority.MEDIUM)).thenReturn(Arrays.asList(mediumPriorityTicket));

        // Act
        ResponseEntity<List<Ticket>> response = itSupportController.getRoutineTicket();

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Arrays.asList(mediumPriorityTicket), response.getBody());
    }
}
