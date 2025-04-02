package vn.edu.stu.laptopshop.service;

import vn.edu.stu.laptopshop.controller.request.user.UserCreateRequest;
import vn.edu.stu.laptopshop.model.UserEntity;

public interface UserService {
    UserEntity addUser(UserCreateRequest request);
    UserEntity getUserByEmail(String email);
}
