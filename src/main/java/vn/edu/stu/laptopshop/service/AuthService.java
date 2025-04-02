package vn.edu.stu.laptopshop.service;

import vn.edu.stu.laptopshop.controller.request.LoginRequest;
import vn.edu.stu.laptopshop.model.UserEntity;

public interface AuthService {
    UserEntity login(LoginRequest loginRequest);
}
