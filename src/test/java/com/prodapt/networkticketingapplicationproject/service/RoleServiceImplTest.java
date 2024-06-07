package com.prodapt.networkticketingapplicationproject.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.prodapt.networkticketingapplicationproject.entities.ERole;
import com.prodapt.networkticketingapplicationproject.entities.Role;
import com.prodapt.networkticketingapplicationproject.exceptions.RoleNotFoundException;
import com.prodapt.networkticketingapplicationproject.repositories.RoleRepository;

@ExtendWith(MockitoExtension.class)
public class RoleServiceImplTest {

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private RoleServiceImpl roleService;

    private Role role;

    @BeforeEach
    void setUp() {
        role = new Role();
        role.setId(1);
        role.setName(ERole.ROLE_USER);
    }

    @Test
    void testFindRoleByName() throws RoleNotFoundException {
        // Arrange
        when(roleRepository.findByName(ERole.ROLE_USER)).thenReturn(Optional.of(role));

        // Act
        Optional<Role> foundRole = roleService.findRoleByName(ERole.ROLE_USER);

        // Assert
        assertTrue(foundRole.isPresent());
        assertEquals(role, foundRole.get());
    }

    @Test
    void testFindRoleById() throws RoleNotFoundException {
        // Arrange
        when(roleRepository.findById(1)).thenReturn(Optional.of(role));

        // Act
        Optional<Role> foundRole = roleService.findRoleById(1);

        // Assert
        assertTrue(foundRole.isPresent());
        assertEquals(role, foundRole.get());
    }

    @Test
    void testFindRoleByName_NotFound() throws RoleNotFoundException {
        // Arrange
        when(roleRepository.findByName(any(ERole.class))).thenReturn(Optional.empty());

        // Act
        Optional<Role> foundRole = roleService.findRoleByName(ERole.ROLE_ADMIN);

        // Assert
        assertTrue(foundRole.isEmpty());
    }

    @Test
    void testFindRoleById_NotFound() throws RoleNotFoundException {
        // Arrange
        when(roleRepository.findById(any(Integer.class))).thenReturn(Optional.empty());

        // Act
        Optional<Role> foundRole = roleService.findRoleById(999);

        // Assert
        assertTrue(foundRole.isEmpty());
    }
}
