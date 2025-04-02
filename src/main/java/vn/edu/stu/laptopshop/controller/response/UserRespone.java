package vn.edu.stu.laptopshop.controller.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import vn.edu.stu.laptopshop.common.UserStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
public class UserRespone {
    private String email;

    private String firstName;

    private String lastName;

    private String phone;

    private UserStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
