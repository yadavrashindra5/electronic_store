package com.lcwd.electronic.store.dtos;

import com.lcwd.electronic.store.validate.ImageNameValid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private String userId;
    @Size(min = 3,max = 15,message = "Invalid Name")
    private String name;
//    @Email(message = "Invalid user email")
    @Pattern(regexp = "^[a-z0-9][-a-z0-9._]+@([-a-z0-9]+\\.)+[a-z]{2,5}$",message = "Invalid user email")
    @NotBlank(message = "email is required")
    private String email;
    @NotBlank(message = "password is required")
    private String password;
    @Size(min = 4 , max = 6,message = "invalid gender")
    private String gender;
    @NotBlank(message = "write something about yourself")
    private String about;
    @ImageNameValid(message = "Image is not valid")
    private String imageName;
}
