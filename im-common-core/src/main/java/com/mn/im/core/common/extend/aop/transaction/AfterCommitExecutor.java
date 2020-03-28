package com.mn.im.core.common.extend.aop.transaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

/**
 * @author Kevin Wu on 2018/8/2.
 * @version 1.0
 */
@Component
public class AfterCommitExecutor extends TransactionSynchronizationAdapter implements Executor {
    private static final Logger logger = LoggerFactory.getLogger(AfterCommitExecutor.class);
    private static final ThreadLocal<List<Runnable>> RUNNABLES = new ThreadLocal<>();

    @Override
    public void execute(Runnable runnable) {
        logger.info("Submitting new runnable {} to run after commit", runnable);
        if (!TransactionSynchronizationManager.isSynchronizationActive()) {
            logger.info("Transaction synchronization is NOT ACTIVE. Executing right now runnable {}", runnable);
            runnable.run();
            return;
        }
        List<Runnable> threadRunnables = RUNNABLES.get();
        if (threadRunnables == null) {
            threadRunnables = new ArrayList<>();
            RUNNABLES.set(threadRunnables);
            TransactionSynchronizationManager.registerSynchronization(this);
        }
        threadRunnables.add(runnable);
    }

    @Override
    public void afterCommit() {
        List<Runnable> threadRunnables = RUNNABLES.get();
        logger.info("Transaction successfully committed, executing {} runnables", threadRunnables.size());
        for (Runnable runnable : threadRunnables) {
            logger.info("Executing runnable {}", runnable);
            try {
                runnable.run();
            } catch (RuntimeException e) {
                logger.error("Failed to execute runnable " + runnable, e);
            }
        }
    }

    @Override
    public void afterCompletion(int status) {
        logger.info("Transaction completed with status {}", status == TransactionSynchronization.STATUS_COMMITTED ? "COMMITTED" : "ROLLED_BACK");
        RUNNABLES.remove();
    }
}
