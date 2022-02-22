package com.myprojects.invoice.mappers;

import com.myprojects.invoice.domain.Users;
import com.myprojects.invoice.domain.dtos.UsersDto;
import com.myprojects.invoice.exceptions.UserNotFoundException;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserMapper {

    public Users mapToUser(final @NotNull UsersDto userDto) throws UserNotFoundException {
        Users user = new Users();
            user.setId(userDto.getId());
            user.setFullName(userDto.getFullName());
            user.setNip(userDto.getNip());
            user.setStreet(userDto.getStreet());
            user.setPostCode(userDto.getPostCode());
            user.setTown(userDto.getTown());
            user.setBank(userDto.getBank());
            user.setPdfPath(userDto.getPdfPath());
            user.setAccountNumber(userDto.getAccountNumber());
            user.setActive(userDto.isActive());
        return user;
    }

    public UsersDto mapToUserDto(final @NotNull Users user) {
        UsersDto userDto = new UsersDto();
            userDto.setId(user.getId());
            userDto.setFullName(user.getFullName());
            userDto.setNip(user.getNip());
            userDto.setStreet(user.getStreet());
            userDto.setPostCode(user.getPostCode());
            userDto.setTown(user.getTown());
            userDto.setBank(user.getBank());
            userDto.setPdfPath(user.getPdfPath());
            userDto.setAccountNumber(user.getAccountNumber());
            userDto.setActive(user.isActive());
        return userDto;
    }

    public List<UsersDto> mapToUserDtoList(final @NotNull List<Users> usersList) {
        return usersList.stream()
                .map(this::mapToUserDto)
                .collect(Collectors.toList());
    }
}