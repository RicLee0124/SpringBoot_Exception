package com.lrm.form;

import com.lrm.domain.User;
import org.springframework.beans.BeanUtils;

public class UserFormConvert implements FormConvert<UserForm,User> {
    @Override
    public User convert(UserForm userForm) {
        User user = new User();
        BeanUtils.copyProperties(userForm,user);
        return user;
    }
}
