package com.crudapp.crudapp.services;

import com.crudapp.crudapp.CourseDao;
import com.crudapp.crudapp.UserRepo;
import com.crudapp.crudapp.entities.Course;
import com.crudapp.crudapp.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService
 {

     //List<Course> list;
     @Autowired
     private  UserRepo userRepo;
     @Autowired
     private CourseDao courseDao;

     private void updateLastUpdatedAt(Course course)
     {
         course.setLastUpdatedAt(new Date());
     }

    @Override
    public List<Course> getCourses()
    {
        //return list;
        return courseDao.findAll();
    }

     @Override
     public Course getCourse(long courseId) {

         Course one = courseDao.getOne(courseId);
         return one;
     }

     @Override
     public Course addCourse(Course course) {
         updateLastUpdatedAt(course);
         //course.setId(courseId);
        courseDao.save(course);
        return course;

     }

     @Override
     public Course updateCourse(Course course,Long courseId) {
         updateLastUpdatedAt(course);
         course.setId(courseId);
         return courseDao.save(course);
     }

     @Override
     public void deleteCourse(long parseLong) {
         Course entity=courseDao.getOne(parseLong);
         courseDao.delete(entity);
     }

     @Override
     public List<Course> findByDescription(String description) {
         return courseDao.findByDescription(description);
     }

     public List<Object[]> getUsersAndCoursesByCourseId(Long courseId) {
         return userRepo.FindUsersAndCoursesByCourseId(courseId);

     }

     public UserCourseDto getUserAndCourseByUserId(Long Id)
     {
         Long courseId= userRepo.FindCourseIdbyUserId(Id);

         if(courseId!=null)
         {
             Course course=courseDao.FindCourseById(courseId);
             User user= userRepo.findById(Id).orElse(null);

             if(user != null && course != null)
             {
                 return  new UserCourseDto(user.getId(), user.getFirstName(), user.getLastName(), user.getAge(),course.getId(),course.getTitle(),course.getDescription());
             }
         }
         return  null;
     }

 }
