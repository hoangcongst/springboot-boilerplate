package com.hoangcongst.sbboilerplate.mongo.model;

import com.conght.common.database.interceptor.model.BaseLogEntityChange;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("LogUser")
public class LogUser extends BaseLogEntityChange {
    public LogUser(BaseLogEntityChange baseLogEntityChange) {
        super(baseLogEntityChange.getEntityName(), baseLogEntityChange.getEntityId(),
                baseLogEntityChange.getUserName(), baseLogEntityChange.getUserId());
        this.setChanges(baseLogEntityChange.getChanges());
    }
}
