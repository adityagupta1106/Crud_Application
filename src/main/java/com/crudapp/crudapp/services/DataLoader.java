package com.crudapp.crudapp.services;

import com.crudapp.crudapp.UserRepo;
import com.crudapp.crudapp.entities.User;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

@Service
@RequiredArgsConstructor
@Slf4j
public class DataLoader {
    @Autowired
    private UserRepo userRepo;


    @Async("taskExecutor")
    public void loadRandomData(int count, CountDownLatch latch) {
        log.info("calling user insertion method");
        for (int i = 0; i < count; i++) {
            User user = generateRandomUser();
            userRepo.save(user);
        }
        log.info("User Saved Successfully");

        latch.countDown();
    }


    private User generateRandomUser() {

        log.info("Random User generation Started");
        Random random = new Random();
        String[] firstNames = {"Aditya", "Ashutosh", "Alice", "Aman"};
        String[] lastNames = {"Gupta", "Kumar", "rai", "Tandon"};
        LocalDateTime dateTime = LocalDateTime.now(ZoneId.systemDefault());
        User user = new User();
        user.setFirstName(firstNames[random.nextInt(firstNames.length)]);
        user.setLastName(lastNames[random.nextInt(lastNames.length)]);
        user.setAge(random.nextInt(10) + 40);
        user.setCourseId(random.nextInt(2000));
        user.setCreatedAt(dateTime);

        String threadInfo = String.format("Thread ID: %d, Name: %s", Thread.currentThread().getId(), Thread.currentThread().getName());
        log.info(threadInfo);

        return user;
    }

//        long end = System.currentTimeMillis();
//        System.out.println("Total Time Taken : " + (end-start));


//    private static final int THREAD_COUNT = 10;
//
//    public DataLoader(UserRepo userRepo) {
//        this.userRepo = userRepo;
//    }

//    @PostConstruct
//    public void loadRandomData() {
//        Thread[] threads = new Thread[THREAD_COUNT];
//        for (int i = 0; i < THREAD_COUNT; i++) {
//            threads[i] = new Thread(() -> {
//                Random random = new Random();
//                String[] firstNames = {"Aditya", "Ashutosh", "Alice", "Aman"};
//                String[] lastNames = {"Gupta", "Kumar", "Rai", "Tandon"};
//                int threadIndex = Integer.parseInt(Thread.currentThread().getName()); // Get the thread's name (index)
//                log.info("Thread {} (ID: {}) started", threadIndex, Thread.currentThread().getId());
//                for (int j = 0; j < 5; j++) {
//                    User user = new User();
//                    user.setFirstName(firstNames[random.nextInt(firstNames.length)]);
//                    user.setLastName(lastNames[random.nextInt(lastNames.length)]);
//                    user.setAge(random.nextInt(10) + 40);
//                    user.setCourseId(random.nextInt(2000));
//                    userRepo.save(user);
//                }
//                log.info("Thread {} (ID: {}) finished", threadIndex, Thread.currentThread().getId());
//            });
//            threads[i].setName(String.valueOf(i)); // Set thread name as index
//            threads[i].start(); // Start the thread
//        }
//
//        for (Thread thread : threads) {
//            try {
//                thread.join(); // Wait for all threads to finish
//            } catch (InterruptedException e) {
//                log.error("Thread interrupted: {}", e.getMessage());
//                Thread.currentThread().interrupt(); // Restore interrupted state
//            }
//        }
//        log.info("Data loading completed.");
//    }
}
