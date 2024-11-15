package com.example.processor.adapter.persistence.dao.user;

import com.example.processor.domain.entity.User;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ListUserDaoImplTest extends UserDaoTest implements UserDaoImplTestSupport {

    @Test
    public void thatEmptyUserTableCanBeListed() {
        assertThat(userDao.listAll()).isEmpty();
    }

    @Test
    public void thatAllUsersCanBeListed() {
        final List<User> insertedUsers = prepareAndInsertUsers(userDao);
        final List<User> listedUsers = userDao.listAll();
        assertThat(listedUsers).isEqualTo(insertedUsers);
    }
}
