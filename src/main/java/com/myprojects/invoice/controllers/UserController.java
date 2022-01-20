package com.myprojects.invoice.controllers;

import com.myprojects.invoice.domain.dtos.UserDto;
import com.myprojects.invoice.exceptions.UserAlreadyExistsException;
import com.myprojects.invoice.exceptions.UserNotFoundException;
import com.myprojects.invoice.facade.UserFacade;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@AllArgsConstructor
@RestController
@RequestMapping("v1/invoices")
public class UserController {

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
    public UserDto updateUser(@PathVariable("id") Long userId, @RequestBody UserDto userDto)
            throws UserNotFoundException {
        return userFacade.updateUser(userId, userDto);
    }
}