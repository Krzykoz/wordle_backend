/**
*  This class is responsible for mapping User entity to UserDto object.
*/

package com.example.wordlegamebackend.user.model.mapper;

import com.example.wordlegamebackend.user.model.dto.UserDto;
import com.example.wordlegamebackend.user.model.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserModelMapper {

    private final ModelMapper modelMapper;

    /**
     * Constructs a new instance of UserModelMapper with the specified ModelMapper.
     *
     * @param modelMapper The ModelMapper instance to use for mapping.
     */
    public UserModelMapper(final ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    /**
     * Maps a User entity to a UserDto object.
     *
     * @param user The User entity to map.
     * @return The mapped UserDto object.
     */
    public UserDto toDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

}
