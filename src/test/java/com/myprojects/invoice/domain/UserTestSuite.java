package com.myprojects.invoice.domain;

import com.myprojects.invoice.repositories.UserRepository;
import com.myprojects.invoice.services.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTestSuite {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Test
    public void shouldFindUserById() {

        // Given
        User user = new User();

        // When
        userRepository.save(user);
        Long id = user.getId();
        Optional<User> foundUser = userRepository.findById(id);

        // Then
        assertNotNull(foundUser);
        assertEquals(id, foundUser.get().getId());

        // Clean Up
        userRepository.deleteById(id);
    }

    @Test
    public void shouldSaveUser() {

        // Given
        User user = new User();
        user.setFullName("Full name");

        // When
        userRepository.save(user);
        Long id = user.getId();
        Optional<User> savedUser = userRepository.findById(id);
        String fullName = savedUser.get().getFullName();

        // Then
        assertEquals("Full name", fullName);

        // Clean Up
        userRepository.deleteById(id);
    }

    @Test
    public void shouldUpdateUser() {

        // Given
        User user = new User();
        User updatedUser = new User();
        user.setFullName("Full name");
        updatedUser.setFullName("Updated full name");

        // When
        userService.save(user);
        Long id = user.getId();
        updatedUser.setId(id);
        userService.update(updatedUser);
        User actualUser = userService.getOne(id);

        // Then
        assertTrue(userRepository.existsById(id));
        assertEquals(updatedUser.getId(), actualUser.getId());
        assertEquals(updatedUser.getFullName(), actualUser.getFullName());
        assertNotEquals(user.getFullName(), actualUser.getFullName());

        // Clean Up
        userRepository.deleteById(id);
    }

    @Test
    public void shouldDeleteUserById() {

        // Given
        User user = new User();

        // When
        userRepository.save(user);
        Long id = user.getId();
        userRepository.deleteById(id);
        Optional<User> removedUser = userRepository.findById(id);
        int availableUsers = userRepository.findAll().size();

        // Then
        assertEquals(Optional.empty(), removedUser);
        assertEquals(0, availableUsers);
    }
}