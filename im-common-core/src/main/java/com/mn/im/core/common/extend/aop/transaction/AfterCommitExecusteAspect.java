package com.mn.im.core.common.extend.aop.transaction;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 事物提交后执行
 *
 * @author Kevin Wu on 2018/8/2.
 * @version 1.0
 */
@Aspect
@Component
public class AfterCommitExecusteAspect {
    private static final Logger logger = LoggerFactory.getLogger(AfterCommitExecusteAspect.class);

    private final AfterCommitExecutor afterCommitExecutor;

    @Autowired
    public AfterCommitExecusteAspect(AfterCommitExecutor afterCommitExecutor) {
        this.afterCommitExecutor = afterCommitExecutor;
    }

    @Around("@annotation(com.qmn.erp.steels.admin.framework.extend.aop.transaction.AfterCommit)")
    public Object aroundAdvice(final ProceedingJoinPoint pjp) {
        afterCommitExecutor.execute(new PjpAfterCommitRunnable(pjp));
        return null;
    }

    private static final class PjpAfterCommitRunnable implements Runnable {
        private final ProceedingJoinPoint pjp;

        public PjpAfterCommitRunnable(ProceedingJoinPoint pjp) {
            this.pjp = pjp;
        }

        @Override
        public void run() {
            try {
                pjp.proceed();
            } catch (Throwable e) {
                logger.error("Exception while invoking pjp.proceed()", e);
                throw new RuntimeException(e);
            }
        }

        @Override
        public String toString() {
            String typeName = pjp.getTarget().getClass().getSimpleName();
            String methodName = pjp.getSignature().getName();
            return "PjpAfterCommitRunnable[type=" + typeName + ", method=" + methodName + "]";
        }
    }
}
