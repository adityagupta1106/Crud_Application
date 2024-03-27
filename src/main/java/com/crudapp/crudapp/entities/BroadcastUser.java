package com.crudapp.crudapp.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BroadcastUser {
    @Id
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;

}
