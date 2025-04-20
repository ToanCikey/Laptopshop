package vn.edu.stu.laptopshop.controller.response.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import vn.edu.stu.laptopshop.common.UserStatus;
import vn.edu.stu.laptopshop.common.UserType;

import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
public class UserResponse {

    private Long id;

    private String email;

    private String fullName;

    private String phone;

    private String image;

    private String address;

    private UserStatus status;

    private UserType type;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
