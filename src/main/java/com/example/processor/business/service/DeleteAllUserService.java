package com.example.processor.business.service;

import com.example.processor.adapter.persistence.dao.user.UserDao;

public class DeleteAllUserService extends AbstractUserService {

    public DeleteAllUserService(UserDao userDao) {
        super(userDao);
    }

    public void execute() {
        userDao.deleteAll();
    }
}
