package com.crudapp.crudapp.services;

import com.crudapp.crudapp.UserAuditHistoryRepo;
import com.crudapp.crudapp.UserRepo;
import com.crudapp.crudapp.entities.User;
import com.crudapp.crudapp.entities.UserAuditHistory;
import com.crudapp.crudapp.json.PutRequestBody;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl {
    @Autowired
    private UserRepo userrepo;
    @Autowired
    private Gson gson;
    @Autowired
    private UserAuditHistoryRepo userAuditHistoryRepo;
    @Autowired
    private UserDaoImpl userDao;


    public User addUser(User user) {
        LocalDateTime localDateTime = LocalDateTime.now(ZoneId.systemDefault());
        user.setLastUpdatedAt(localDateTime);
        user.setCreatedAt(localDateTime);
        userrepo.save(user);
        // Create audit history for the added user
        createAuditHistory(user);

        return user;

    }

    public void createAuditHistory(User user) {
        // setting user audit history
        LocalDateTime date = LocalDateTime.now(ZoneId.systemDefault());
        UserAuditHistory userEntry = new UserAuditHistory();
        userEntry.setUserId(user.getId());
        userEntry.setCreatedAt(date);
        userEntry.setUpdatedAt(date);
        userEntry.setMetaData(null);
        userEntry.setRemarks("New user created");
        userAuditHistoryRepo.save(userEntry);


    }

//    public User updateUser(User user,Long userId) {
//
//        log.info("searching for User Id: " +userId);
//        Optional<User> optionalUser=userrepo.findById(userId);
//        if(optionalUser.isPresent())
//        {
//            log.info("User Found: "+gson.toJson(optionalUser.get()));
//            User userToBeUpdated=optionalUser.get();
//            userToBeUpdated.setFirstName(user.getFirstName());
//            userToBeUpdated.setLastName(user.getLastName());
//            userToBeUpdated.setAge(user.getAge());
//            userToBeUpdated.setCourseId(user.getCourseId());
//            LocalDateTime localDateTime = LocalDateTime.now();
//            userToBeUpdated.setLastUpdatedAt(Timestamp.valueOf(localDateTime));
//
//            return userrepo.save(userToBeUpdated);
//        }
//        return null;
//    }

    public void updateUser(Long userId, PutRequestBody requestBody) throws JsonProcessingException {
            userDao.update(userId, requestBody);
    }


    public List<User> getUser() {
        //return list;
        return userrepo.findAll();
    }
}
