package com.crudapp.crudapp;

import com.crudapp.crudapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Long> {

}
