package com.myprojects.invoice.services;

import com.myprojects.invoice.domain.User;
import com.myprojects.invoice.exceptions.UserAlreadyExistsException;
import com.myprojects.invoice.exceptions.UserNotFoundException;
import com.myprojects.invoice.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public List<User> getAll() {
        return userRepository.findAll().stream()
                .filter(u -> !u.isDeleted())
                .collect(Collectors.toList());
    }

    public User getOne(final Long id) throws UserNotFoundException {
        return userRepository.findById(id)
                .filter(u -> !u.isDeleted())
                .orElseThrow(UserNotFoundException::new);
    }

    public User save(final @NotNull User user) throws UserAlreadyExistsException {
        Long id = user.getId();
        if (id != null && userRepository.existsById(id)) {
            throw new UserAlreadyExistsException();
        }
        return userRepository.save(user);
    }

    public User update(final @NotNull User user) throws UserNotFoundException {
        Long id = user.getId();
        if (id == null || !userRepository.existsById(id)) {
            throw new UserNotFoundException();
        }
        return userRepository.save(user);
    }

    public void delete(final Long id) throws UserNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
        user.setDeleted(true);
        userRepository.save(user);
    }
}