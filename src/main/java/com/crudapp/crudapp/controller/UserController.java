package com.crudapp.crudapp.controller;

import com.crudapp.crudapp.UserRepo;
import com.crudapp.crudapp.entities.Course;
import com.crudapp.crudapp.entities.User;
import com.crudapp.crudapp.json.PutRequestBody;
import com.crudapp.crudapp.services.CourseService;
import com.crudapp.crudapp.services.CourseServiceImpl;
import com.crudapp.crudapp.services.UserCourseDto;
import com.crudapp.crudapp.services.UserServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@Slf4j
@RequestMapping("/v1/crud-users")
public class
UserController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private CourseServiceImpl courseServices;
    @Autowired
    private UserServiceImpl userserviceImpl;
    @Autowired
    private Gson gson;
    @Autowired
    private UserRepo userRepo;

    // get the courses

    @PostMapping("/users")
    public User addUser(@RequestBody User user)
    {
        return userserviceImpl.addUser(user);
    }

//    @PutMapping("/user/{userId}")
//    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable Long userId)
//    {
//        try {
//            User updatedUser= userservice.updateUser(user,userId);
//            return ResponseEntity.ok(updatedUser);
//        }catch (NullPointerException e)
//        {
//            log.error("user does not exist. exception is : ",e);
//        }
//        return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
//    }


    @PutMapping("/users")
    public ResponseEntity<Optional<User>> updateUser(@RequestBody PutRequestBody requestBody) throws JsonProcessingException {
        Long userId = requestBody.getUserId();
        userserviceImpl.updateUser(userId, requestBody);
        Optional<User> updatedUser=userRepo.findById(userId);
        if (updatedUser.isPresent()) {
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/users")
    public List<User> getAllUser()
    {
        return userserviceImpl.getUser();

    }

    @GetMapping("/user/{userId}")
    public User getUserById(@PathVariable Long userId)
    {

       Optional<User> user= userRepo.findById(userId);
        return user.orElse(null);

    }



    //CourseController


    @GetMapping("/courses")
    public List<Course> getCourses()
    {
     return this.courseService.getCourses();

    }
    @GetMapping("/courses/{courseId}")
    public Course getCourse(@PathVariable String courseId)
    {
        return this.courseService.getCourse(Long.parseLong(courseId));
    }
    @PostMapping("/courses")
    public Course addCourse(@RequestBody Course course)
    {
        return this.courseService.addCourse(course);
    }
    @PutMapping("/courses/{courseId}")
    public Course updateCourse(@RequestBody Course course, @PathVariable Long courseId)
    {
        return this.courseService.updateCourse(course,courseId);
    }
    @DeleteMapping("/courses/{courseId}")
    public ResponseEntity<HttpStatus> deleteCourse(@PathVariable String courseId)
    {
        try{
            this.courseService.deleteCourse(Long.parseLong(courseId));
            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/courses/by-description")
    public List<Course> getByDescription(@RequestParam String description) {
        return this.courseService.findByDescription(description);
    }
    @GetMapping("/users-and-courses")
    public List<Object[]> getUsersAndCoursesByCourseId(@RequestParam Long courseId){
        return courseServices.getUsersAndCoursesByCourseId(courseId);
    }
    @GetMapping("/user-course")
    public UserCourseDto getUserAndCourseByUserId(@RequestParam Long Id)
    {
        return courseServices.getUserAndCourseByUserId(Id);
    }

}
