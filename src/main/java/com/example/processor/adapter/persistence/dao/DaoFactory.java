package com.example.processor.adapter.persistence.dao;

import com.example.processor.adapter.persistence.dao.user.UserDao;
import com.example.processor.domain.Databases;

import java.util.Optional;

public abstract class DaoFactory {

    public abstract UserDao getUserDao();

    public static Optional<DaoFactory> getDaoFactory(Databases db) {
        switch (db) {
            case H2:
                return Optional.of(new H2DaoFactory());
            default:
                return Optional.empty();
        }
    }

    public static DaoFactory getDefaultDaoFactory() {
        return getDaoFactory(Databases.H2).orElseThrow();
    }
}
