package vn.edu.stu.laptopshop.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.stu.laptopshop.controller.request.LoginRequest;
import vn.edu.stu.laptopshop.controller.request.user.UserCreateRequest;
import vn.edu.stu.laptopshop.controller.response.ResponseSuccess;
import vn.edu.stu.laptopshop.controller.response.UserResponse;
import vn.edu.stu.laptopshop.mapper.UserMapper;
import vn.edu.stu.laptopshop.model.UserEntity;
import vn.edu.stu.laptopshop.service.AuthService;
import vn.edu.stu.laptopshop.service.UserService;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Tag(name = "Auth Controller")
@Slf4j(topic = "AUTH-CONTROLLER")
@Validated
public class AuthController {
    private final AuthService authService;
    private final UserService userService;
    private final UserMapper userMapper;

    @Operation(summary = "Login", description = "API login user")
    @PostMapping("/login")
    public ResponseSuccess<UserResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        UserEntity userEntity = authService.login(loginRequest);
        return new ResponseSuccess<>(HttpStatus.OK.value(), "Login success", userMapper.toUserResponse(userEntity));
    }

    @Operation(summary = "Register", description = "API register user")
    @PostMapping("/register")
    public ResponseSuccess<UserResponse> register(@Valid @RequestBody UserCreateRequest request) {
        UserEntity user = userService.addUser(request);
        return new ResponseSuccess<>(HttpStatus.CREATED.value(),"Create User successful", userMapper.toUserResponse(user));
    }
}
