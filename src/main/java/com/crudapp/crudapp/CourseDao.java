package com.crudapp.crudapp;

import com.crudapp.crudapp.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseDao extends JpaRepository<Course,Long> {
@Query(value="SELECT * FROM Course WHERE description=:description", nativeQuery = true)
    List<Course> findByDescription(String description);

}
