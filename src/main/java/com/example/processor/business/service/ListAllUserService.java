package com.example.processor.business.service;

import com.example.processor.adapter.persistence.dao.user.UserDao;
import com.example.processor.domain.entity.User;

import java.util.List;

public class ListAllUserService extends AbstractUserService {

    public ListAllUserService(UserDao userDao) {
        super(userDao);
    }

    public List<User> execute() {
        return userDao.listAll();
    }
}
