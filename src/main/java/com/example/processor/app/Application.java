package com.example.processor.app;

import com.example.processor.business.processor.ProducerConsumerExecutor;
import com.example.processor.domain.command.Command;

import java.util.List;
import java.util.UUID;

import static com.example.processor.business.utils.UserCommandUtils.*;

public class Application {

    public static void main(String[] args) {
        List<Command> commandSequence = List.of(
                createAddUserCommand(UUID.randomUUID(), "a1", "Robert"),
                createAddUserCommand(UUID.randomUUID(), "a2", "Martin"),
                createPrintAllUserCommand(),
                createDeleteAllUserCommand(),
                createPrintAllUserCommand());

        ProducerConsumerExecutor pce = new ProducerConsumerExecutor(commandSequence);
        pce.execute();
    }
}
