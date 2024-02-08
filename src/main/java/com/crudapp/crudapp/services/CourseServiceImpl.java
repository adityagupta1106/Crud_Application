package com.crudapp.crudapp.services;

import com.crudapp.crudapp.CourseDao;
import com.crudapp.crudapp.entities.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService
 {

     //List<Course> list;
     @Autowired
     private CourseDao courseDao;
     public CourseServiceImpl()
     {
//         list=new ArrayList<>();
//         list.add(new Course(145, "Java Core Course", "this is course of java"));
//         list.add(new Course(149, "Spring Boot Course", "this is course of spring boot"));
     }
    @Override
    public List<Course> getCourses()
    {
        //return list;
        return courseDao.findAll();
    }

     @Override
     public Course getCourse(long courseId) {
//         Course c=null;
//         for(Course course:list)
//         {
//             if(course.getId()==courseId)
//             {
//                 c=course;
//                 break;
//             }
//         }
         //return c;
         Course one = courseDao.getOne(courseId);
         return one;
     }

     @Override
     public Course addCourse(Course course) {
//         list.add(course);
//         return course;
        courseDao.save(course);
        return course;

     }

     @Override
     public Course updateCourse(Course course) {
//         list.forEach(e->{
//             if(e.getId()==course.getId())
//             {
//                 e.setTitle(course.getTitle());
//                 e.setDescription(course.getDescription());
//             }
//         });
         return course;
     }

     @Override
     public void deleteCourse(long parseLong) {
        // list=this.list.stream().filter(e->e.getId()!=parseLong).collect(Collectors.toList());
         Course entity=courseDao.getOne(parseLong);
         courseDao.delete(entity);

     }

     @Override
     public List<Course> findByDescription(String description) {
         return courseDao.findByDescription(description);
     }


//     @Override
//     public List<Course> findByDescription(String description) {
//         Course one=courseDao.findbyDescrition(description);
//         return null;
//     }

 }
