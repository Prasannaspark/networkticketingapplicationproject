package com.prodapt.networkticketingapplicationproject.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.prodapt.networkticketingapplicationproject.entities.ERole;
import com.prodapt.networkticketingapplicationproject.entities.Role;
import com.prodapt.networkticketingapplicationproject.entities.Ticket;
import com.prodapt.networkticketingapplicationproject.exceptions.RoleNotFoundException;
import com.prodapt.networkticketingapplicationproject.exceptions.TicketNotFoundException;
import com.prodapt.networkticketingapplicationproject.service.RoleService;
import com.prodapt.networkticketingapplicationproject.service.TicketService;
import com.prodapt.networkticketingapplicationproject.service.UserEntityService;

@ExtendWith(MockitoExtension.class)
public class AdminControllerTest {

    @Mock
    private UserEntityService userService;

    @Mock
    private RoleService roleService;

    @Mock
    private TicketService ticketService;

    @InjectMocks
    private AdminController adminController;

    private Role role;
    private Ticket ticket;
    private List<Ticket> tickets;

    @BeforeEach
    void setUp() {
        role = new Role();
        role.setId(1);
        role.setName(ERole.ROLE_USER);

        ticket = new Ticket();
        ticket.setTicketId(1L);

        tickets = Arrays.asList(ticket);
    }

    @Test
    void testUpdateUserRole_Success() throws RoleNotFoundException {
        // Arrange
        when(roleService.findRoleByName(ERole.ROLE_USER)).thenReturn(Optional.of(role));
        when(userService.updaterole(any(Long.class), any(Role.class))).thenReturn("Role Updated Successfully!!!");

        // Act
        ResponseEntity<String> response = adminController.updateUserRole(1L, ERole.ROLE_USER);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Role Updated Successfully!!!", response.getBody());
    }

    @Test
    void testGetAllTickets_Success() throws TicketNotFoundException {
        // Arrange
        when(ticketService.getAllTickets()).thenReturn(tickets);

        // Act
        ResponseEntity<List<Ticket>> response = adminController.getAll();

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(tickets, response.getBody());
    }

    @Test
    void testGetAllTickets_NoTickets() throws TicketNotFoundException {
        // Arrange
        when(ticketService.getAllTickets()).thenReturn(Arrays.asList());

        // Act
        ResponseEntity<List<Ticket>> response = adminController.getAll();

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().isEmpty());
    }
}
