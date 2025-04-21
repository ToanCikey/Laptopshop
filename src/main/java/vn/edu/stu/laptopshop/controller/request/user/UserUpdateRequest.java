package vn.edu.stu.laptopshop.controller.request.user;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import vn.edu.stu.laptopshop.common.UserStatus;
import vn.edu.stu.laptopshop.common.UserType;

@Getter
@Setter
public class UserUpdateRequest {
    @NotNull(message = "Id must be not null")
    @Min(value = 1, message = "Id must be at least 1")
    private Long id;

    @NotBlank(message = "FullName must be not blank")
    @Size(min = 3, message = "FullName minimum 3 characters")
    private String fullName;

    @Size(min = 10, message = "Phone minimum 10 characters")
    private String phone;

    @Size(min = 10, message = "Address minimum 10 characters")
    private String address;

    private UserStatus status;

    private UserType type;
}
