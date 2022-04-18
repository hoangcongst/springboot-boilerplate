package com.hoangcongst.sbboilerplate.config;

import com.hoangcongst.sbboilerplate.auth.model.User;
import com.hoangcongst.sbboilerplate.util.SpringContextUtil;
import com.conght.common.database.interceptor.BaseEntityAuditLogInterceptor;
import com.conght.common.database.interceptor.model.BaseLogEntityChange;
import com.conght.common.database.interceptor.model.UserInfoLog;
import lombok.val;
import org.springframework.core.env.Environment;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class EntityAuditLogInterceptor extends BaseEntityAuditLogInterceptor {
    @Override
    public UserInfoLog getUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            User mUser = ((User)principal);
            return new UserInfoLog(mUser.getId(), mUser.getUsername());
        }
        return new UserInfoLog(0L, "");
    }

    @Override
    public void writeLog(BaseLogEntityChange baseLogEntityChange) {
        val env = SpringContextUtil.getApplicationContext().getBean(Environment.class);
        if(!env.getProperty("interceptor.database.status", "enable").equals("enable"))
            return;
        val logRepository = SpringContextUtil.getApplicationContext().getBean(env.getProperty("interceptor.database.repository.prefix", "log")
                + baseLogEntityChange.getEntityName() + env.getProperty("interceptor.database.repository.suffix", "Repository"));
        try {
            Class<?> cls = Class.forName(env.getProperty("interceptor.database.model.prefix", "Log") + baseLogEntityChange.getEntityName());
            Method insertLog = logRepository.getClass().getMethod("insert", Object.class);
            Object mLog = cls.getConstructor(BaseLogEntityChange.class).newInstance(baseLogEntityChange);
            insertLog.invoke(logRepository, mLog);
        } catch (NoSuchMethodException | ClassNotFoundException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
