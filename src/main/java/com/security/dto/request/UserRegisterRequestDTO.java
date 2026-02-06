package com.security.dto.request;


import com.security.entity.Role;
import com.security.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRegisterRequestDTO {
    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Password is required")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", message = "Invalid password format")
    private String password;

    private Boolean enabled=true;

    private Boolean accountNonLocked=true;

    private Boolean accountNonExpired=true;

    public User convertToUser(){
        return new User().builder().firstName(firstName).lastName(lastName).email(email).password(password).role(Role.USER).enabled(true).accountNonLocked(true).accountNonExpired(true).build();

    }

}
