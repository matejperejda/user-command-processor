package com.example.processor.domain.command;

import com.example.processor.domain.entity.User;
import org.immutables.value.Value;

@Value.Immutable
public interface AddUserCommand extends Command {

    User user();
}
