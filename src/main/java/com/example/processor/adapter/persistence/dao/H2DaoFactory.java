package com.example.processor.adapter.persistence.dao;

import com.example.processor.adapter.persistence.dao.user.UserDao;
import com.example.processor.adapter.persistence.dao.user.UserDaoImpl;

public class H2DaoFactory extends DaoFactory {

    @Override
    public UserDao getUserDao() {
        return new UserDaoImpl();
    }
}
