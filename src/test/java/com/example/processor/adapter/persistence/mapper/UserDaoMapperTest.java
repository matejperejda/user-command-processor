package com.example.processor.adapter.persistence.mapper;

import com.example.processor.domain.entity.User;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserDaoMapperTest {

    @Test
    public void thatUserCanBeMappedSuccessfully() {
        final UUID id = UUID.randomUUID();
        final String guid = "a1";
        final String name = "user-name";

        User user = UserDaoMapper.map(id, guid, name);
        assertThat(user.id()).isEqualTo(id);
        assertThat(user.guid()).isEqualTo(guid);
        assertThat(user.name()).isEqualTo(name);
    }

    @Test
    public void thatUserNameCannotBeEmpty() {
        assertThrows(IllegalArgumentException.class, () -> UserDaoMapper.map(UUID.randomUUID(), "a1", ""), "Name cannot be empty.");
    }
}
