package com.example.processor.adapter.persistence.mapper;

import com.example.processor.domain.entity.ImmutableUser;
import com.example.processor.domain.entity.User;

import java.util.UUID;

public class UserDaoMapper {

    public static User map(UUID id, String guid, String name) {
        return ImmutableUser.of(id, guid, name);
    }
}
