package com.scm.forms;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserForms {

    @NotBlank(message="User Name is required")
    @Size(message="Minimum 3 character is required", min=3)
    private String name;

    @Email(message="Invalid Email")
    @NotBlank(message="Enter your Email")
    private String email;

    @NotBlank
    @Size(min=6,message="Minimum 6 character is required")
    private String password;
    private String about;

    @Size(min=10,max=12,message="Invalid PhoneNumber")
    private String phoneNumber;

}
