package com.example.processor.adapter.persistence.dao.user;

import com.example.processor.domain.entity.User;

import java.util.List;

public interface UserDao {

    void insert(User user);

    void deleteAll();

    List<User> listAll();
}
