package com.tml.log4j2demo.log4j2;

import org.apache.logging.log4j.ThreadContext;

import java.util.Map;
import java.util.concurrent.Callable;

public class ThreadContextUtil {
    
    // 设置 traceId
    public static void setTraceId(String traceId) {
        ThreadContext.put("traceId", traceId);
    }
    
    // 获取 traceId
    public static String getTraceId() {
        return ThreadContext.get("traceId");
    }
    
    // 清除所有上下文
    public static void clear() {
        ThreadContext.clearAll();
    }
    
    // 包装 Runnable 以传递上下文
    public static Runnable wrap(final Runnable runnable) {
        // 捕获当前线程的上下文
        final Map<String, String> context = ThreadContext.getContext();
        return () -> {
            try {
                // 设置父线程的上下文
                ThreadContext.putAll(context);
                runnable.run();
            } finally {
                // 恢复原始上下文
                ThreadContext.clearMap();
            }
        };
    }
    
    // 包装 Callable 以传递上下文
    public static <T> Callable<T> wrap(final Callable<T> callable) {
        final Map<String, String> context = ThreadContext.getContext();
        return () -> {
            try {
                ThreadContext.putAll(context);
                return callable.call();
            } finally {
                ThreadContext.clearMap();
            }
        };
    }
}