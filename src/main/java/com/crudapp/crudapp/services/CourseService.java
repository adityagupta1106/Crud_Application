package com.crudapp.crudapp.services;

import com.crudapp.crudapp.entities.Course;

import java.util.List;

public interface CourseService {
    public List<Course> getCourses();

    public Course getCourse(long courseId);

    public Course addCourse(Course course);

   public Course updateCourse(Course course);

   public void deleteCourse(long parseLong);

    List<Course> findByDescription(String java);

    // List<Course> findByDescription(String description);
}
