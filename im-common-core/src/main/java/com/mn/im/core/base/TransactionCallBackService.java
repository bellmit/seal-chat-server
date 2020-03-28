package com.mn.im.core.base;

import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * @author qiaomengnan
 * @ClassName: TransactionCallBackService
 * @Description:
 * @date 2020-01-14
 * 参考： https://www.jianshu.com/p/d74863539a44
 */
@Component
public class TransactionCallBackService {

    public void execute(final CallBackAction action) {

        if (TransactionSynchronizationManager.isActualTransactionActive()) {
            TransactionSynchronizationManager
                    .registerSynchronization(new TransactionSynchronizationAdapter() {
                        @Override
                        public void afterCommit() {
                            // 事务提交后执行回调
                            action.callback();
                        }
                    });
        } else {
            // 事务提交后执行回调
            action.callback();
        }

    }

}
