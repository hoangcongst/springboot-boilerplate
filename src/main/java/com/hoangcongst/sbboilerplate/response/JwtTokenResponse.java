package com.hoangcongst.sbboilerplate.response;

import com.hoangcongst.sbboilerplate.model.User;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

public class JwtTokenResponse extends BaseResponse {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final String token;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final User user;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final Date expired;

    public JwtTokenResponse(int status, String token, Date expired, User user) {
        super(status);
        this.token = token;
        this.expired = expired;
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public Date getExpired() {
        return expired;
    }

    public User getUser() { return user; }
}