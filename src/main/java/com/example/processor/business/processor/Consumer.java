package com.example.processor.business.processor;

import com.example.processor.business.service.ResolveCommandService;
import com.example.processor.domain.command.Command;
import com.example.processor.domain.command.PoisonPillCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {

    private static final Logger log = LoggerFactory.getLogger(Consumer.class);

    private final BlockingQueue<Command> queue;
    private final ResolveCommandService resolveCommandService;

    public Consumer(BlockingQueue<Command> queue,
                    ResolveCommandService resolveCommandService) {
        this.queue = queue;
        this.resolveCommandService = resolveCommandService;
    }

    @Override
    public void run() {
        final long threadId = Thread.currentThread().threadId();
        try {
            while (true) {
                final Command command = queue.take();
                log.info("[{}]: Consumed {}", threadId, command);

                if (command instanceof PoisonPillCommand poisonPill) {
                    // spreading a poison pill among other consumer threads to stop consuming
                    queue.put(poisonPill);
                    break;
                }
                resolveCommandService.execute(command, threadId);
            }
        } catch (InterruptedException e) {
            log.warn("[{}]: Consumer was interrupted.", threadId);
        }
    }
}
