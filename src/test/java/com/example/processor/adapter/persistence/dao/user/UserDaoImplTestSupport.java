package com.example.processor.adapter.persistence.dao.user;

import com.example.processor.domain.entity.ImmutableUser;
import com.example.processor.domain.entity.User;

import java.util.List;
import java.util.UUID;

public interface UserDaoImplTestSupport {

    default ImmutableUser prepareUser() {
        return ImmutableUser.of(UUID.randomUUID(), "a1", "user-name");
    }

    default List<User> prepareAndInsertUsers(UserDao userDao) {
        User user1 = prepareUser().withName("name-1");
        userDao.insert(user1);

        User user2 = prepareUser().withName("name-2");
        userDao.insert(user2);

        User user3 = prepareUser().withName("name-3");
        userDao.insert(user3);

        return List.of(user1, user2, user3);
    }
}
