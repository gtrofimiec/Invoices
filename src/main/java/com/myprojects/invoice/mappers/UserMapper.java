package com.myprojects.invoice.mappers;

import com.myprojects.invoice.domain.User;
import com.myprojects.invoice.domain.dtos.UserDto;
import com.myprojects.invoice.exceptions.UserNotFoundException;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserMapper {

    public User mapToUser(final @NotNull UserDto userDto) throws UserNotFoundException {
        User user = new User();
            user.setId(userDto.getId());
            user.setFullName(userDto.getFullName());
            user.setNip(userDto.getNip());
            user.setStreet(userDto.getStreet());
            user.setPostCode(userDto.getPostCode());
            user.setTown(userDto.getTown());
        return user;
    }

    public UserDto mapToUserDto(final @NotNull User user) {
        UserDto userDto = new UserDto();
            userDto.setId(user.getId());
            userDto.setFullName(user.getFullName());
            userDto.setNip(user.getNip());
            userDto.setStreet(user.getStreet());
            userDto.setPostCode(user.getPostCode());
            userDto.setTown(user.getTown());
        return userDto;
    }

    public List<UserDto> mapToUserDtoList(final @NotNull List<User> usersList) {
        return usersList.stream()
                .map(this::mapToUserDto)
                .collect(Collectors.toList());
    }
}