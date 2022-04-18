package com.hoangcongst.sbboilerplate.auth.vo;
import com.hoangcongst.sbboilerplate.vo.BaseIndexRequest;
import lombok.Setter;

import javax.validation.constraints.Size;

@Setter
public class AdminIndexRequest extends BaseIndexRequest {
    @Size(min = 1)
    private final String name;

    public AdminIndexRequest(String name, Integer page, Integer size) {
        super(page, size);
        this.name = encryptDataRequest(name);
    }

    public String getName() {
        return encryptDataRequest(name);
    }
}
