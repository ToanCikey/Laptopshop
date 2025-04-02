package vn.edu.stu.laptopshop.common;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum UserStatus {
    ACTIVE, INACTIVE, NONE;

    @JsonCreator
    public static UserStatus fromString(String value) {
        return UserStatus.valueOf(value.toUpperCase());
    }
}
