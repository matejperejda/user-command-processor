package com.example.processor.adapter.persistence.dao.user;

import com.example.processor.domain.entity.User;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class InsertUserDaoImplTest extends UserDaoTest implements UserDaoImplTestSupport {

    @Test
    public void thatUserCanBeInsertedAndListed() {
        final User userToInsert = prepareUser();
        userDao.insert(userToInsert);

        List<User> users = userDao.listAll();
        assertThat(users).containsExactly(userToInsert);
    }

    @Test
    public void thatUserWithSameIdCannotBeInserted() {
        final User user1 = prepareUser();
        userDao.insert(user1);

        List<User> users = userDao.listAll();
        assertThat(users).containsExactly(user1);

        final User user2 = prepareUser().withId(user1.id());
        assertThrows(RuntimeException.class, () -> userDao.insert(user2));

        users = userDao.listAll();
        assertThat(users).containsExactly(user1);
    }
}
