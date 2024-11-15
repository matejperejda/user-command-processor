package com.example.processor.business.service;

import com.example.processor.adapter.persistence.dao.user.UserDao;
import com.example.processor.domain.entity.User;

public class AddUserService extends AbstractUserService {

    public AddUserService(UserDao userDao) {
        super(userDao);
    }

    public void execute(User user) {
        userDao.insert(user);
    }
}
