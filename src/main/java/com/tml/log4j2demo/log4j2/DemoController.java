package com.tml.log4j2demo.log4j2;

import org.apache.logging.log4j.ThreadContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.Executor;

@RestController
public class DemoController {
    Logger logger = LoggerFactory.getLogger(DemoController.class);

    @Autowired
    private AsyncService asyncService;

    @Autowired
    private Executor threadPoolExecutor;

    @RequestMapping("/testLog")
    public String testLog(){
        logger.info("主线程请求开始");

        // 同步调用
        asyncService.syncMethod();

        // 异步调用（会丢失上下文）
        asyncService.asyncMethod();

        // 自定义线程池调用（保持上下文）
        asyncService.executeTask(() -> {
            logger.info("自定义线程任务");
        });

        for (int i=0;i<10;i++){
            threadPoolExecutor.execute(ThreadContextUtil.wrap(()->{
                logger.info("线程池----");
            }));
        }


        logger.info("主线程请求结束");
        return "查看控制台日志输出";
    }
}
