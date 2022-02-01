package com.myprojects.invoice.facade;

import com.myprojects.invoice.domain.Users;
import com.myprojects.invoice.domain.dtos.UsersDto;
import com.myprojects.invoice.exceptions.UserAlreadyExistsException;
import com.myprojects.invoice.exceptions.UserNotFoundException;
import com.myprojects.invoice.mappers.UserMapper;
import com.myprojects.invoice.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class UserFacade {

    private final UserMapper userMapper;
    private final UserService userService;

    public List<UsersDto> getUsers() {
        return userMapper.mapToUserDtoList(userService.getAll());
    }

    public UsersDto getUser(Long userId) throws UserNotFoundException {
        return userMapper.mapToUserDto(userService.getOne(userId));
    }

    public UsersDto saveUser(UsersDto userDto) throws UserNotFoundException,
            UserAlreadyExistsException {
        Users newUser = userMapper.mapToUser(userDto);
        userService.save(newUser);
        return userMapper.mapToUserDto(newUser);
    }

    public UsersDto updateUser(Long userId, UsersDto userDto)
            throws UserNotFoundException {
        Users updatedUser = userMapper.mapToUser(userDto);
        updatedUser.setId(userId);
        userService.update(updatedUser);
        return userMapper.mapToUserDto(updatedUser);
    }

    public void deleteUser(Long userId) throws UserNotFoundException {
        userService.delete(userId);
    }
}