package com.hoangcongst.sbboilerplate.model;

import org.springframework.beans.factory.annotation.Value;

public interface UserSecretInfo {
    String getUsername();
    @Value("#{target.display_name}")
    String getDisplayName();
    String getAvatar();
    String getEmail();
}
