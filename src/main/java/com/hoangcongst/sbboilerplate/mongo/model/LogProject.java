package com.hoangcongst.sbboilerplate.mongo.model;

import com.conght.common.database.interceptor.model.BaseLogEntityChange;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("LogProject")
public class LogProject extends BaseLogEntityChange {
    public LogProject(BaseLogEntityChange baseLogEntityChange) {
        super(baseLogEntityChange.getEntityName(), baseLogEntityChange.getEntityId(),
                baseLogEntityChange.getUserName(), baseLogEntityChange.getUserId());
        this.setChanges(baseLogEntityChange.getChanges());
    }
}
