package com.crudapp.crudapp.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private long Id;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private int age;
    @Column(nullable = false)
    private long courseId;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime lastUpdatedAt;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;
}
