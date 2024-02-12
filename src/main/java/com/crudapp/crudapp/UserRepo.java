package com.crudapp.crudapp;

import com.crudapp.crudapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepo extends JpaRepository<User,Long> {
 @Query(value="SELECT u.* , c.* FROM User u JOIN Course c ON u.course_id=c.id WHERE u.course_id=:courseId",nativeQuery = true)
     List<Object[]> FindUsersAndCoursesByCourseId(Long courseId);
    //List<User> findUsersByCourseId(String courseId);
}
