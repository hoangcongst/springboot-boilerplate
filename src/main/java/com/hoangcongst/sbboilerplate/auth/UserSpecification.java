package com.hoangcongst.sbboilerplate.auth;

import com.hoangcongst.sbboilerplate.auth.model.User;
import com.conght.common.requestcriteria.BaseSpecification;
import com.conght.common.requestcriteria.util.SpecSearchCriteria;

public class UserSpecification extends BaseSpecification<User> {

    public UserSpecification(SpecSearchCriteria criteria) {
        super(criteria);
    }
}
