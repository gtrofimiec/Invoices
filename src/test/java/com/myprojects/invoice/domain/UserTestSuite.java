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
    public void shouldFindAllUsers() {

        // Given
        long currentNumberOfUsers = usersRepository.findAll().stream()
                .filter(c -> !c.isDeleted())
                .count();
        Users user1 = new Users();
        Users user2 = new Users();

        // When
        usersRepository.save(user1);
        usersRepository.save(user2);
        long availableUsers = usersRepository.findAll().stream()
                .filter(c -> !c.isDeleted())
                .count();

        // Then
        assertEquals(currentNumberOfUsers + 2, availableUsers);

        // Clean Up
        usersRepository.deleteById(user1.getId());
        usersRepository.deleteById(user2.getId());
    }

    @Test
    public void shouldFindUserById() {

        // Given
        Users user1 = new Users();
        Users user2 = new Users();

        // When
        usersRepository.save(user1);
        usersRepository.save(user2);
        Long user1Id = user1.getId();
        Optional<Users> foundUser = usersRepository.findById(user1Id);

        // Then
        assertNotNull(foundUser);
        assertEquals(user1Id, foundUser.get().getId());

        // Clean Up
        usersRepository.deleteById(user1Id);
        usersRepository.deleteById(user2.getId());
    }

    @Test
    public void shouldSaveUser() {

        // Given
        Users user1 = new Users();
        Users user2 = new Users();
        user1.setFullName("User1");
        user2.setFullName("User2");

        // When
        usersRepository.save(user1);
        usersRepository.save(user2);
        Long User1Id = user1.getId();
        Long User2Id = user2.getId();
        Optional<Users> savedUser1 = usersRepository.findById(User1Id);
        Optional<Users> savedUser2 = usersRepository.findById(User2Id);
        String fullName1 = savedUser1.get().getFullName();
        String fullName2 = savedUser2.get().getFullName();

        // Then
        assertEquals("User1", fullName1);
        assertEquals("User2", fullName2);

        // Clean Up
        usersRepository.deleteById(User1Id);
        usersRepository.deleteById(User2Id);
    }

    @Test
    public void shouldUpdateUser() {

        // Given
        Users user = new Users();

        // When
        userService.save(user);
        user.setFullName("Full name");
        userService.update(user);
        Long Userid = user.getId();
        Users actualUser = userService.getOne(Userid);

        // Then
        assertTrue(usersRepository.existsById(Userid));
        assertEquals("Full name", actualUser.getFullName());

        // Clean Up
        usersRepository.deleteById(Userid);
    }

    @Test
    public void shouldDeleteUserById() {

        // Given
        long currentNumberOfUsers = usersRepository.findAll().stream()
                .filter(c -> !c.isDeleted())
                .count();
        Users user1 = new Users();
        Users user2 = new Users();

        // When
        usersRepository.save(user1);
        usersRepository.save(user2);
        Long user1Id = user1.getId();
        usersRepository.deleteById(user1Id);
        Optional<Users> removedUser = usersRepository.findById(user1Id);
        long availableUsers = usersRepository.findAll().stream()
                .filter(c -> !c.isDeleted())
                .count();

        // Then
        assertEquals(Optional.empty(), removedUser);
        assertEquals(currentNumberOfUsers + 1, availableUsers);
    }
}