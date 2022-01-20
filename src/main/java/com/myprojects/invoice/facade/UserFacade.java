package com.myprojects.invoice.facade;

import com.myprojects.invoice.domain.User;
import com.myprojects.invoice.domain.dtos.UserDto;
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

    private final UserService userService;
    private final UserMapper userMapper;

    public List<UserDto> getUsers() {
        return userMapper.mapToUserDtoList(userService.getAll());
    }

    public UserDto getUser(Long id) throws UserNotFoundException {
        return userMapper.mapToUserDto(userService.getOne(id));
    }

    public UserDto createUser(UserDto userDto) throws UserNotFoundException,
            UserAlreadyExistsException {
        User newUser = userMapper.mapToUser(userDto);
        userService.save(newUser);
        return userMapper.mapToUserDto(newUser);
    }

    public UserDto updateUser(Long id, UserDto userDto)
            throws UserNotFoundException {
        User updatedUser = userMapper.mapToUser(userDto);
        updatedUser.setId(id);
        userService.update(updatedUser);
        return userMapper.mapToUserDto(updatedUser);
    }
}