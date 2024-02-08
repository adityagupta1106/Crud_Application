package com.crudapp.crudapp;

import com.crudapp.crudapp.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseDao extends JpaRepository<Course,Long> {

    List<Course> findByDescription(String description);

}
