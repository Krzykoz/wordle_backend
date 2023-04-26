package com.piaskowy.urlshortenerbackend.user.model.mapper;

import com.piaskowy.urlshortenerbackend.user.model.dto.UserDto;
import com.piaskowy.urlshortenerbackend.user.model.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserModelMapper {

    private final ModelMapper modelMapper;

    public UserModelMapper(final ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public UserDto toDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

}
