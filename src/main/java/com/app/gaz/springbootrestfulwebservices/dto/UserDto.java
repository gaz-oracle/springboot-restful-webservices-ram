package com.app.gaz.springbootrestfulwebservices.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;

    /** User First Name should not be null or empty*/
    @NotEmpty(message = "User First Name should not be null or empty atte GazApp" )
    private String firstName;

    /** User Last  Name should not be null or empty*/
    @NotEmpty (message = "User Last  Name should not be null or empty atte GazApp")
    private String lastName;

    /** User Emails should not be null or empty*/
    @NotEmpty (message = "User Emails should not be null or empty atte GazApp")
    @Email    (message = "Email address should be valid")
    private String email;

}
