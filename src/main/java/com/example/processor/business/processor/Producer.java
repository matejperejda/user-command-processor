package com.example.processor.business.processor;

import com.example.processor.domain.command.Command;
import com.example.processor.domain.command.ImmutablePoisonPillCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {

    private static final Logger log = LoggerFactory.getLogger(Producer.class);

    private final BlockingQueue<Command> queue;
    private final List<Command> commands;

    public Producer(BlockingQueue<Command> queue, List<Command> commands) {
        this.queue = queue;
        this.commands = commands;
    }

    @Override
    public void run() {
        final long threadId = Thread.currentThread().threadId();
        try {
            putCommandToQueue(commands, threadId);
            sendPoisonPill();
        } catch (InterruptedException e) {
            log.warn("[{}]: Producer was interrupted.", threadId);
        }
    }

    private void putCommandToQueue(List<Command> commands, long threadId) throws InterruptedException {
        for (Command command : commands) {
            queue.put(command);
            log.info("[{}]: Produced {}", threadId, command);
        }
    }

    private void sendPoisonPill() throws InterruptedException {
        queue.put(ImmutablePoisonPillCommand.builder().build());
    }
}
