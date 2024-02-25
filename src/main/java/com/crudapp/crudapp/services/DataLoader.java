package com.crudapp.crudapp.services;

import com.crudapp.crudapp.UserRepo;
import com.crudapp.crudapp.entities.User;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class DataLoader {

    private final UserRepo userRepo;
    //@PostConstruct
    public void  init(){
        loadData();
    }
    long start=0;
    public void loadData()
    {
        int totalRecods=100000;
        int batchSize=1000;
        start = System.currentTimeMillis();
        for (int i=0;i<totalRecods/batchSize;i++)
        {
            saveDataBtach(batchSize);
        }
        long end = System.currentTimeMillis();
        System.out.println("Total Time Taken : " + (end-start));
    }
    @Async("taskExecutor")
    public void saveDataBtach(int batchSize)
    {
        Random random=new Random();
        String[] firstNames={"Aditya","Ashutosh","Alice","Aman"};
        String[] lastNames={"Gupta","Kumar","rai","Tandon"};

        for(int i=0;i<batchSize;i++){
            User user=new User();
            user.setFirstName(firstNames[random.nextInt(firstNames.length)]);
            user.setLastName(lastNames[random.nextInt(lastNames.length)]);
            user.setAge(random.nextInt(10)+40);
            user.setCourseId(random.nextInt(2000));

            userRepo.save(user);
        }
    }

    //@PostConstruct
//    public void loadRandomData() {
//        Random random=new Random();
//        String[] firstNames={"Aditya","Ashutosh","Alice","Aman"};
//        String[] lastNames={"Gupta","Kumar","rai","Tandon"};
//        //Long courseIds= new Long(1500);
//        long start = System.currentTimeMillis();
//        for(int i=0;i<500000;i++)
//        {
//            User user=new User();
//            user.setFirstName(firstNames[random.nextInt(firstNames.length)]);
//            user.setLastName(lastNames[random.nextInt(lastNames.length)]);
//            user.setAge(random.nextInt(10)+40);
//            user.setCourseId(random.nextInt(2000));
//
//            userRepo.save(user);
//       }
//        long end = System.currentTimeMillis();
//        System.out.println("Total Time Taken : " + (end-start));
//    }
}
