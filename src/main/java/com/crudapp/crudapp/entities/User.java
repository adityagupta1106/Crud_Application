package com.crudapp.crudapp.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long Id;
    private String firstName;
    private String lastName;
    private int age;
    private long courseId;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime lastUpdatedAt;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;
}
