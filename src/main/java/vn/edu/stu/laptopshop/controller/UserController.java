package vn.edu.stu.laptopshop.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vn.edu.stu.laptopshop.controller.request.user.UserCreateRequest;
import vn.edu.stu.laptopshop.controller.request.user.UserUpdateRequest;
import vn.edu.stu.laptopshop.controller.response.ResponseSuccess;
import vn.edu.stu.laptopshop.mapper.UserMapper;
import vn.edu.stu.laptopshop.model.UserEntity;
import vn.edu.stu.laptopshop.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@Tag(name = "User Controller")
@Slf4j(topic = "USER-CONTROLLER")
@Validated
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @Operation(summary = "Create User", description = "API create user to database")
    @PostMapping("/create")
    public ResponseSuccess<?> createUser(@Valid @RequestBody UserCreateRequest request) {
        UserEntity user = userService.addUser(request);
        return new ResponseSuccess<>(HttpStatus.CREATED.value(),"Create User successful", userMapper.toUserResponse(user));
    }

    @Operation(summary = "Update User", description = "API update user to database")
    @PutMapping("/update")
    public ResponseSuccess<?> updateUser(@Valid @RequestBody UserUpdateRequest request) {
        userService.updateUser(request);

        return new ResponseSuccess<>(HttpStatus.NO_CONTENT.value(),"Update User successful");
    }

    @Operation(summary = "Lock User", description = "API lock user to database")
    @PostMapping("/lock/{id}")
    public ResponseSuccess<?> lockUser(@Min(1) @PathVariable Long id) {
        userService.lockUser(id);

        return new ResponseSuccess<>(HttpStatus.NO_CONTENT.value(),"Lock User successful");
    }

    @Operation(summary = "GetAll User", description = "API get all user to database")
    @GetMapping("/list")
    public ResponseSuccess<?> getAllUsers() {
        List<UserEntity> users = userService.getAllUsers();
        return new ResponseSuccess<>(HttpStatus.OK.value(),"Getall users to database", userMapper.toListUserResponse(users));
    }
}
