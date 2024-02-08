package com.crudapp.crudapp.controller;

import com.crudapp.crudapp.entities.Course;
import com.crudapp.crudapp.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class MyController {
    @Autowired
    private CourseService courseService;
    // get the courses
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
@PutMapping("/courses")
    public Course updateCourse(@RequestBody Course course)
    {
        return this.courseService.updateCourse(course);
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

//    @GetMapping("/by-desciption")
//    public List<Course> getbyDescription(@RequestParam String description){
//        return this.courseService.findByDescription(description);
//    }

}
