package com.example.processor.business.service;

import com.example.processor.domain.command.AddUserCommand;
import com.example.processor.domain.command.Command;
import com.example.processor.domain.command.DeleteAllUserCommand;
import com.example.processor.domain.command.PrintAllUserCommand;
import com.example.processor.domain.entity.User;

import java.util.List;

public class ResolveCommandService {

    private final AddUserService addUserService;
    private final DeleteAllUserService deleteAllUserService;
    private final ListAllUserService listAllUserService;

    public ResolveCommandService(AddUserService addUserService,
                                 DeleteAllUserService deleteAllUserService,
                                 ListAllUserService listAllUserService) {
        this.addUserService = addUserService;
        this.deleteAllUserService = deleteAllUserService;
        this.listAllUserService = listAllUserService;
    }

    public void execute(Command command, Long workerId) {
        if (command instanceof AddUserCommand addCommand) {
            callAddUserService(addCommand, workerId);
        } else if (command instanceof DeleteAllUserCommand) {
            callDeleteAllUserService(workerId);
        } else if (command instanceof PrintAllUserCommand) {
            callListAllUserService(workerId);
        }
    }

    private void callAddUserService(AddUserCommand command, Long workerId) {
        addUserService.execute(command.user());
        System.out.printf("[%s]: User %s added.%n", workerId, command.user());
    }

    private void callDeleteAllUserService(Long workerId) {
        deleteAllUserService.execute();
        System.out.printf("[%s]: All users deleted.%n", workerId);
    }

    private void callListAllUserService(Long workerId) {
        List<User> users = listAllUserService.execute();
        System.out.printf("[%s]: All users: %s%n", workerId, users);
    }
}
