package com.crudapp.crudapp.services;

import com.crudapp.crudapp.UserRepo;
import com.crudapp.crudapp.entities.User;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    private final UserRepo userRepo;
    @Async("taskExecutor")
    public  void loadRandomData(int count) {
        try {
            for (int i = 0; i < count; i++) {
                User user = generateRandomUser();
                if (user != null) {
                    userRepo.save(user);
                }
            }
        } catch (Exception e){
            log.error("An error occurred while saving user :", e);

        }
        //latch.countDown();
    }


    private User generateRandomUser() {
        Random random = new Random();
        String[] firstNames = {"Aditya", "Ashutosh", "Alice", "Aman"};
        String[] lastNames = {"Gupta", "Kumar", "rai", "Tandon"};
        try {
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
        } catch (Exception e){
            log.error("Error occurred in Generating Random User: ",e);
            return null;
        }
    }


}
