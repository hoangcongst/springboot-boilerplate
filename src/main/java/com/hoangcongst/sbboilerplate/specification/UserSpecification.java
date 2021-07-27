package com.hoangcongst.sbboilerplate.specification;

import com.hoangcongst.sbboilerplate.model.User;
import com.conght.common.requestcriteria.BaseSpecification;
import com.conght.common.requestcriteria.util.SpecSearchCriteria;

public class UserSpecification extends BaseSpecification<User> {

    public UserSpecification(SpecSearchCriteria criteria) {
        super(criteria);
    }
}
