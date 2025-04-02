package vn.edu.stu.laptopshop.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import vn.edu.stu.laptopshop.controller.response.UserRespone;
import vn.edu.stu.laptopshop.model.UserEntity;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private final ModelMapper modelMapper;

    public UserRespone toUserRespone(UserEntity user) {
        return modelMapper.map(user, UserRespone.class);
    }
}
