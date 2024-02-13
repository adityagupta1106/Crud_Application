package com.crudapp.crudapp.services;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserCourseDto {
    private Long userId;
    private String firstName;
    private String lastName;
    private int age;
    private Long courseId;
    private String courseName;
    private  String courseDescription;
}
