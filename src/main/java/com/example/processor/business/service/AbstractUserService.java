package com.example.processor.business.service;

import com.example.processor.adapter.persistence.dao.user.UserDao;

public abstract class AbstractUserService {

    protected final UserDao userDao;

    public AbstractUserService(UserDao userDao) {
        this.userDao = userDao;
    }
}
