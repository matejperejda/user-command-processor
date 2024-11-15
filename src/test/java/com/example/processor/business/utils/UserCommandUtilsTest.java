package com.example.processor.business.utils;

import com.example.processor.business.utils.UserCommandUtils;
import com.example.processor.domain.command.AddUserCommand;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserCommandUtilsTest {

    @Test
    public void thatAddUserCommandCanBeCreated() {
        UUID id = UUID.randomUUID();
        String guid = "guid";
        String name = "name";

        final AddUserCommand command = UserCommandUtils.createAddUserCommand(id, guid, name);
        assertThat(command.user()).isNotNull();
        assertThat(command.user().id()).isEqualTo(id);
        assertThat(command.user().guid()).isEqualTo(guid);
        assertThat(command.user().name()).isEqualTo(name);
    }

    @Test
    public void thatAddUserCommandCannotBeCreatedWithoutName() {
        assertThrows(IllegalArgumentException.class, () -> UserCommandUtils.createAddUserCommand(UUID.randomUUID(), "guid", ""), "Name cannot be empty.");
    }

    @Test
    public void thatDeleteAllUserCommandCanBeCreated() {
        assertThat(UserCommandUtils.createDeleteAllUserCommand()).isNotNull();
    }

    @Test
    public void thatPrintAllUserCommandCanBeCreated() {
        assertThat(UserCommandUtils.createPrintAllUserCommand()).isNotNull();
    }
}
