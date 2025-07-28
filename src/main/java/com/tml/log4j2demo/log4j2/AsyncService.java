package com.tml.log4j2demo.log4j2;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
public class AsyncService {
    
    private static final Logger logger = LogManager.getLogger(AsyncService.class);

    // 正常同步方法
    public void syncMethod() {
        logger.info("syncMethod");
    }

    // 异步方法 - 需要传递上下文
    @Async("threadPoolExecutor")
    public void asyncMethod() {
        logger.info("asyncMethod");
    }



    // 自定义线程池执行任务
    public void executeTask(Runnable task) {
        // 使用包装器传递上下文
        new Thread(ThreadContextUtil.wrap(task)).start();
    }
}