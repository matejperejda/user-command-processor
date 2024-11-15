package com.example.processor.business.processor;

import com.example.processor.adapter.persistence.dao.DaoFactory;
import com.example.processor.adapter.persistence.dao.user.UserDao;
import com.example.processor.business.service.AddUserService;
import com.example.processor.business.service.DeleteAllUserService;
import com.example.processor.business.service.ListAllUserService;
import com.example.processor.business.service.ResolveCommandService;
import com.example.processor.domain.command.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class ProducerConsumerExecutor {

    private static final Logger log = LoggerFactory.getLogger(ProducerConsumerExecutor.class);

    private static final int THREADS_COUNT = 2;

    private final BlockingQueue<Command> queue;
    private final List<Command> commandsSequence;

    public ProducerConsumerExecutor(List<Command> commandSequence) {
        this.commandsSequence = commandSequence;
        this.queue = new LinkedBlockingQueue<>(commandSequence.size());
    }

    public void execute() {
        try (ExecutorService executor = Executors.newFixedThreadPool(THREADS_COUNT)) {
            UserDao userDao = DaoFactory.getDefaultDaoFactory().getUserDao();
            ResolveCommandService resolveCommandService = new ResolveCommandService(new AddUserService(userDao),
                    new DeleteAllUserService(userDao), new ListAllUserService(userDao));

            // we consider 1 producer and 1 consumer due to sequential processing of database operations (commands in queue)
            executor.execute(new Thread(new Producer(queue, commandsSequence)));
            executor.execute(new Thread(new Consumer(queue, resolveCommandService)));
            executor.shutdown();
        } catch (IllegalArgumentException e) {
            log.error("Unexpected exception while creating an ExecutorService.", e);
            throw new RuntimeException("Unexpected exception while creating an ExecutorService.");
        }
    }
}
