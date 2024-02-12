package com.crudapp.crudapp.services;

import com.crudapp.crudapp.UserRepo;
import com.crudapp.crudapp.entities.User;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class DataLoader {

    private final UserRepo userRepo;

    @PostConstruct
    public void loadRandomData() {
//        Random random=new Random();
//        String[] firstNames={"Aditya","Ashutosh","Alice","Aman"};
//        String[] lastNames={"Gupta","Kumar","rai","Tandon"};
//        //Long courseIds= new Long(1500);
//
//        for(int i=0;i<20000;i++)
//        {
//            User user=new User();
//            user.setFirstName(firstNames[random.nextInt(firstNames.length)]);
//            user.setLastName(lastNames[random.nextInt(lastNames.length)]);
//            user.setAge(random.nextInt(10)+40);
//            user.setCourseId(random.nextInt(1,2000));
//
//            userRepo.save(user);
//        }

    }
}
