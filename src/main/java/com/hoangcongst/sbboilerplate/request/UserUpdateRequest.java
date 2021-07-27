package com.hoangcongst.sbboilerplate.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserUpdateRequest {
    @Size(max = 255)
    @Email
    @NotNull
    private String email;
    @Size(min = 6, max = 50)
    @NotNull
    private String display_name;
    @Size(min = 8, max = 50)
    private String password;

    public UserUpdateRequest(String email, String display_name, String password) {
        this.email = email;
        this.display_name = display_name;
        this.password = password;
    }
}
