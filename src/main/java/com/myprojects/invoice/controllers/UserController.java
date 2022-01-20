package com.myprojects.invoice.controllers;

import com.myprojects.invoice.domain.User;
import com.myprojects.invoice.domain.dtos.UserDto;
import com.myprojects.invoice.exceptions.UserAlreadyExistsException;
import com.myprojects.invoice.exceptions.UserNotFoundException;
import com.myprojects.invoice.facade.UserFacade;
import com.myprojects.invoice.mappers.UserMapper;
import com.myprojects.invoice.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@AllArgsConstructor
@RestController
@RequestMapping("v1/invoices")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;
    private final UserFacade userFacade;

    @RequestMapping(method = RequestMethod.GET, value = "/users")
    public List<UserDto> getUsers() {
        return userFacade.getUsers();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/users/{id}")
    public UserDto getUser(@PathVariable("id") Long userId) throws UserNotFoundException {
        return userFacade.getUser(userId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/users",
            consumes = APPLICATION_JSON_VALUE)
    public UserDto createUser(@RequestBody UserDto userDto) throws UserNotFoundException,
            UserAlreadyExistsException {
        return userFacade.createUser(userDto);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/users/{id}")
    public UserDto updateUser(@PathVariable("id") Long id, @RequestBody UserDto userDto)
            throws UserNotFoundException {
        User updatedUser = userMapper.mapToUser(userDto);
        updatedUser.setId(id);
        userService.update(updatedUser);
        return userMapper.mapToUserDto(updatedUser);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/users/{id}")
    public void deleteUser(@PathVariable("id") Long id) throws UserNotFoundException {
        userService.delete(id);
    }
}