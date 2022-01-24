package com.myprojects.invoice.controllers;

import com.myprojects.invoice.domain.dtos.UsersDto;
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
    public List<UsersDto> getUsers() {
        return userFacade.getUsers();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/users/{id}")
    public UsersDto getUser(@PathVariable("id") Long userId) throws UserNotFoundException {
        return userFacade.getUser(userId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/users",
            consumes = APPLICATION_JSON_VALUE)
    public UsersDto createUser(@RequestBody UsersDto userDto) throws UserNotFoundException,
            UserAlreadyExistsException {
        return userFacade.createUser(userDto);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/users/{id}")
    public UsersDto updateUser(@PathVariable("id") Long userId, @RequestBody UsersDto userDto)
            throws UserNotFoundException {
        return userFacade.updateUser(userId, userDto);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/users/{id}")
    public void deleteUser(@PathVariable("id") Long userId)
            throws UserNotFoundException {
        userFacade.deleteUser(userId);
    }
}