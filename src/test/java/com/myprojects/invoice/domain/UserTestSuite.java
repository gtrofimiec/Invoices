package com.myprojects.invoice.domain;

import com.myprojects.invoice.repositories.UsersRepository;
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
    private UsersRepository usersRepository;

    @Autowired
    private UserService userService;

    @Test
    public void shouldFindUserById() {

        // Given
        Users user = new Users();

        // When
        usersRepository.save(user);
        Long id = user.getId();
        Optional<Users> foundUser = usersRepository.findById(id);

        // Then
        assertNotNull(foundUser);
        assertEquals(id, foundUser.get().getId());

        // Clean Up
        usersRepository.deleteById(id);
    }

    @Test
    public void shouldSaveUser() {

        // Given
        Users user = new Users();
        user.setFullName("Full name");

        // When
        usersRepository.save(user);
        Long id = user.getId();
        Optional<Users> savedUser = usersRepository.findById(id);
        String fullName = savedUser.get().getFullName();

        // Then
        assertEquals("Full name", fullName);

        // Clean Up
        usersRepository.deleteById(id);
    }

    @Test
    public void shouldUpdateUser() {

        // Given
        Users user = new Users();
        Users updatedUser = new Users();
        user.setFullName("Full name");
        updatedUser.setFullName("Updated full name");

        // When
        userService.save(user);
        Long id = user.getId();
        updatedUser.setId(id);
        userService.update(updatedUser);
        Users actualUser = userService.getOne(id);

        // Then
        assertTrue(usersRepository.existsById(id));
        assertEquals(updatedUser.getId(), actualUser.getId());
        assertEquals(updatedUser.getFullName(), actualUser.getFullName());
        assertNotEquals(user.getFullName(), actualUser.getFullName());

        // Clean Up
        usersRepository.deleteById(id);
    }

    @Test
    public void shouldDeleteUserById() {

        // Given
        Users user = new Users();

        // When
        usersRepository.save(user);
        Long id = user.getId();
        usersRepository.deleteById(id);
        Optional<Users> removedUser = usersRepository.findById(id);
        int availableUsers = usersRepository.findAll().size();

        // Then
        assertEquals(Optional.empty(), removedUser);
        assertEquals(0, availableUsers);
    }
}