package com.example.processor.business.utils;

import com.example.processor.domain.command.*;
import com.example.processor.domain.entity.ImmutableUser;

import java.util.UUID;

public class UserCommandUtils {

    public static AddUserCommand createAddUserCommand(UUID id, String guid, String name) {
        return ImmutableAddUserCommand.builder()
                .user(ImmutableUser.of(id, guid, name))
                .build();
    }

    public static PrintAllUserCommand createPrintAllUserCommand() {
        return ImmutablePrintAllUserCommand.builder().build();
    }

    public static DeleteAllUserCommand createDeleteAllUserCommand() {
        return ImmutableDeleteAllUserCommand.builder().build();
    }
}
