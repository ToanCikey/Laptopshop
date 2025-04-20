package vn.edu.stu.laptopshop.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import vn.edu.stu.laptopshop.controller.response.user.UserResponse;
import vn.edu.stu.laptopshop.model.UserEntity;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private final ModelMapper modelMapper;

    public UserResponse toUserResponse(UserEntity user) {
        return modelMapper.map(user, UserResponse.class);
    }

    public List<UserResponse> toListUserResponse(List<UserEntity> users) {
        return users.stream()
                .map(user -> modelMapper.map(user, UserResponse.class))
                .toList();
    }
}
