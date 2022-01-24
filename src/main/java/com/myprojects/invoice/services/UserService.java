package com.myprojects.invoice.services;

import com.myprojects.invoice.domain.Users;
import com.myprojects.invoice.exceptions.UserAlreadyExistsException;
import com.myprojects.invoice.exceptions.UserNotFoundException;
import com.myprojects.invoice.repositories.UsersRepository;
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

    private final UsersRepository usersRepository;

    public List<Users> getAll() {
        return usersRepository.findAll().stream()
                .filter(u -> !u.isDeleted())
                .collect(Collectors.toList());
    }

    public Users getOne(final Long id) throws UserNotFoundException {
        return usersRepository.findById(id)
                .filter(u -> !u.isDeleted())
                .orElseThrow(UserNotFoundException::new);
    }

    public Users save(final @NotNull Users user) throws UserAlreadyExistsException {
        Long id = user.getId();
        if (id != null && usersRepository.existsById(id)) {
            throw new UserAlreadyExistsException();
        }
        return usersRepository.save(user);
    }

    public Users update(final @NotNull Users user) throws UserNotFoundException {
        Long id = user.getId();
        if (id == null || !usersRepository.existsById(id)) {
            throw new UserNotFoundException();
        }
        return usersRepository.save(user);
    }

    public void delete(final Long id) throws UserNotFoundException {
        Users user = usersRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
        user.setDeleted(true);
        usersRepository.save(user);
    }
}