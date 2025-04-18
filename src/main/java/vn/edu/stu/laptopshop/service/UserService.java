package vn.edu.stu.laptopshop.service;

import vn.edu.stu.laptopshop.controller.request.user.UserCreateRequest;
import vn.edu.stu.laptopshop.controller.request.user.UserUpdateRequest;
import vn.edu.stu.laptopshop.model.UserEntity;

import java.util.List;

public interface UserService {
    UserEntity addUser(UserCreateRequest request);
    UserEntity getUserByEmail(String email);
    void updateUser(UserUpdateRequest request);
    void lockUser(Long id);
    UserEntity getUserById(Long id);
    List<UserEntity> getAllUsers();
}
