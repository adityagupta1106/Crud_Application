package com.crudapp.crudapp;

import asyncConfig.AsyncConfig;
import com.crudapp.crudapp.services.DataLoader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;

@SpringBootApplication
// todo about this annotation
//@EnableAsync
@Slf4j
@Import(AsyncConfig.class)
public class CrudappApplication implements CommandLineRunner {
    @Autowired
    private DataLoader dataLoader;

    public static void main(String[] args) {
        SpringApplication.run(CrudappApplication.class, args);
    }

    @Override
    public void run(String... args) throws InterruptedException {
        int totalUsers = 500000;
        int thread = 30;

        int userPerThread = totalUsers / thread;

        long start = System.currentTimeMillis();
        CountDownLatch latch = new CountDownLatch(thread);
        for (int i = 0; i < thread; i++) {
            log.info("Started Thread No : " + i);
            dataLoader.loadRandomData(userPerThread, latch);
        }

        latch.await();
        long end = System.currentTimeMillis();
        log.info("Total Time Taken : " + (end - start));
    }

}
