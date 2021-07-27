package com.hoangcongst.sbboilerplate.request;

import com.hoangcongst.sbboilerplate.util.MysqlSHAUtil;
import lombok.Data;

import javax.validation.constraints.Min;

@Data
public class BaseIndexRequest {
    private int page;
    @Min(1)
    private int size;

    public BaseIndexRequest(Integer page, Integer size) {
        this.page = page != null ? page : 0;
        this.size = size != null ? size : 15;
    }

    protected String encryptDataRequest(String rawData) {
//        if(SearchOperation.SIMPLE_OPERATION_SET)
        return MysqlSHAUtil.encrypt(rawData);
    }
}
