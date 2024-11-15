package com.example.processor.adapter.persistence.dao.user;

import com.example.processor.domain.entity.User;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DeleteAllUserDaoImplTest extends UserDaoTest implements UserDaoImplTestSupport {

    @Test
    public void thatAllUsersCanBeDeleted() {
        assertThat(userDao.listAll()).isEmpty();

        List<User> users = prepareAndInsertUsers(userDao);
        assertThat(userDao.listAll()).isEqualTo(users);

        userDao.deleteAll();
        assertThat(userDao.listAll()).isEmpty();
    }

    @Test
    public void thatAllUsersCanBeDeletedIfTableIsEmpty() {
        assertThat(userDao.listAll()).isEmpty();
        userDao.deleteAll();
        assertThat(userDao.listAll()).isEmpty();
    }
}
