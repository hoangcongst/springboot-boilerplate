package com.hoangcongst.sbboilerplate.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserCreateRequest {
    @NotNull
    @Size(min = 5, max = 20)
    private String username;
    @NotNull
    @Size(max = 255)
    @Email
    private String email;
    @NotNull
    @Size(min = 6, max = 50)
    private String display_name;
    @NotNull
    @Size(min = 8, max = 50)
    private String password;
    private byte status;

    public UserCreateRequest(String username, String email, String display_name, String password, MultipartFile avatar) {
        this.username = username;
        this.email = email;
        this.display_name = display_name;
        this.password = password;
    }
}
