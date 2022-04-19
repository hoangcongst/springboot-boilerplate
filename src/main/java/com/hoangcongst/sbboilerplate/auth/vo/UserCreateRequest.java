package com.hoangcongst.sbboilerplate.auth.vo;

import lombok.Data;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserCreateRequest {
    @NotNull
    @Size(min = 5, max = 20)
    private String displayName;
    @NotNull
    @Size(max = 255)
    @Email
    private String email;
    @NotNull
    @Size(min = 8, max = 50)
    private String password;
}
