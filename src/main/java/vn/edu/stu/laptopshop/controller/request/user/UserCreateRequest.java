package vn.edu.stu.laptopshop.controller.request.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import vn.edu.stu.laptopshop.common.UserStatus;

@Getter
@Setter
public class UserCreateRequest {
    @Email
    @NotBlank(message = "Email must be not blank")
    private String email;

    @NotBlank(message = "Password must be not blank")
    @Size(min = 6, message = "Password minimum 6 characters")
    private String password;

    @NotBlank(message = "FirstName must be not blank")
    @Size(min = 3, message = "FirstName minimum 3 characters")
    private String firstName;

    @NotBlank(message = "LastName must be not blank")
    @Size(min = 3, message = "LastName minimum 3 characters")
    private String lastName;

    @NotBlank(message = "Phone must be not blank")
    @Size(min = 10, message = "Phone minimum 10 characters")
    private String phone;

    @NotNull(message = "Userstatus must not be null")
    private UserStatus status;
}
