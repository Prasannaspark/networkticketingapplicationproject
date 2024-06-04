package com.prodapt.networkticketingapplicationproject.service;

import static org.junit.jupiter.api.Assertions.*;
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
import com.prodapt.networkticketingapplicationproject.entities.UserEntity;
import com.prodapt.networkticketingapplicationproject.repositories.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserEntityServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserEntityServiceImpl userEntityService;

    private UserEntity user;
    private Role role;

    @BeforeEach
    void setUp() {
        role = new Role();
        role.setId(1);
        role.setName(ERole.ROLE_USER);

        user = new UserEntity();
        user.setId(1L);
        user.setUsername("testuser");
        user.setEmail("testuser@example.com");
        user.setRole(role);
    }

    @Test
    void testUpdaterole_UserExists() {
        // Arrange
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.save(any(UserEntity.class))).thenReturn(user);

        // Act
        String result = userEntityService.updaterole(1L, role);

        // Assert
        assertEquals("Role Updated Successfully!!!", result);
    }

    @Test
    void testUpdaterole_UserNotExists() {
        // Arrange
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        // Act
        String result = userEntityService.updaterole(1L, role);

        // Assert
        assertNull(result);
    }

    @Test
    void testGetUserById_UserExists() {
        // Arrange
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        // Act
        UserEntity foundUser = userEntityService.getUserById(1L);

        // Assert
        assertNotNull(foundUser);
        assertEquals(user, foundUser);
    }

    @Test
    void testGetUserById_UserNotExists() {
        // Arrange
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        // Act
        UserEntity foundUser = userEntityService.getUserById(1L);

        // Assert
        assertNull(foundUser);
    }

    @Test
    void testFindByUsername_UserExists() {
        // Arrange
        when(userRepository.findByUsername("testuser")).thenReturn(Optional.of(user));

        // Act
        Optional<UserEntity> foundUser = userEntityService.findByUsername("testuser");

        // Assert
        assertTrue(foundUser.isPresent());
        assertEquals(user, foundUser.get());
    }


    @Test
    void testExistsByUsername_True() {
        // Arrange
        when(userRepository.existsByUsername("testuser")).thenReturn(true);

        // Act
        Boolean exists = userEntityService.existsByUsername("testuser");

        // Assert
        assertTrue(exists);
    }

    @Test
    void testExistsByUsername_False() {
        // Arrange
        when(userRepository.existsByUsername("testuser")).thenReturn(false);

        // Act
        Boolean exists = userEntityService.existsByUsername("testuser");

        // Assert
        assertFalse(exists);
    }

    @Test
    void testExistsByEmail_True() {
        // Arrange
        when(userRepository.existsByEmail("testuser@example.com")).thenReturn(true);

        // Act
        Boolean exists = userEntityService.existsByEmail("testuser@example.com");

        // Assert
        assertTrue(exists);
    }

    @Test
    void testExistsByEmail_False() {
        // Arrange
        when(userRepository.existsByEmail("testuser@example.com")).thenReturn(false);

        // Act
        Boolean exists = userEntityService.existsByEmail("testuser@example.com");

        // Assert
        assertFalse(exists);
    }

    @Test
    void testFindByRole_UserExists() {
        // Arrange
        when(userRepository.findByRole(ERole.ROLE_USER)).thenReturn(Optional.of(user));

        // Act
        Optional<UserEntity> foundUser = userEntityService.findByRole(ERole.ROLE_USER);

        // Assert
        assertTrue(foundUser.isPresent());
        assertEquals(user, foundUser.get());
    }


    @Test
    void testAddUserEntity() {
        // Arrange
        when(userRepository.save(any(UserEntity.class))).thenReturn(user);

        // Act
        UserEntity savedUser = userEntityService.addUserEntity(user);

        // Assert
        assertNotNull(savedUser);
        assertEquals(user, savedUser);
    }
}
