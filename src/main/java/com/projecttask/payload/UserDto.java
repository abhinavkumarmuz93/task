package com.projecttask.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private long id;

    @NotBlank(message = "Name is required")
    @Pattern(regexp = "[a-zA-Z]+", message = "Name should only contain letters")//
    private String name;

    @NotBlank(message = "Date of birth is required")
    private String dateOfBirth;

    @Min(value = 25, message = "Age must be at least 25")
    @Max(value = 45, message = "Age must be less than or equal to 45")
    private int age;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Mobile number is required")
    @Pattern(regexp = "\\d+", message = "Mobile number should only contain digits")
    private String mobile;

    @NotBlank(message = "User type is required")
    @Pattern(regexp = "^(Developer|HR|Manager)$", message = "Invalid user type")
    private String userType;


}

