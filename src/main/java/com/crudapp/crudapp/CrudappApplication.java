package com.crudapp.crudapp;
import com.crudapp.crudapp.config.AsyncConfig;
import com.crudapp.crudapp.services.DataLoader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
@Slf4j
@EnableAsync
@Import(AsyncConfig.class)
@EnableTransactionManagement
public class CrudappApplication  {
    @Autowired
    private DataLoader dataLoader;
    @Value("${data.total-users}")
     private int totalUsers;
    @Value("${data.thread-count}")
    private int threadCount;
    public static void main(String[] args) {
        SpringApplication.run(CrudappApplication.class, args);
    }
   // @PostConstruct
    @Transactional
    public void loadData() throws InterruptedException {
        int userPerThread = totalUsers / threadCount;
        int usersForLastThread = userPerThread + totalUsers % threadCount; // Handling remainder users
        //CountDownLatch latch = new CountDownLatch(threadCount);

        long start = System.currentTimeMillis();

        for (int i = 0; i < threadCount; i++) {
            if (i == threadCount - 1) {
                dataLoader.loadRandomData(usersForLastThread); // Last thread takes remainder
            } else {
                dataLoader.loadRandomData(userPerThread);
            }
            log.info("Started Thread No : " + i);
        }

      //  latch.await(); // Wait for all threads to complete
        long end = System.currentTimeMillis();

        log.info("Total Time Taken : " + (end - start) + " ms");
    }

}

