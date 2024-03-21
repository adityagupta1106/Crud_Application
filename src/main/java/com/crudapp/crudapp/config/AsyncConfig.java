package com.crudapp.crudapp.config;

import java.util.concurrent.Executor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
// create a logger which collect a application log files
@Configuration
@EnableAsync
@Slf4j
public class AsyncConfig {
    @Bean(name = "taskExecutor")
    public Executor taskExecutor() {
        log.info("Creating async Task Executor");
        ThreadPoolTaskExecutor executor =new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(40);
        executor.setMaxPoolSize(50);
        executor.setQueueCapacity(100);
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setAwaitTerminationSeconds(30);
        executor.setThreadNamePrefix("LoadDataThread-");
        executor.initialize();

        return executor;
    }
}
