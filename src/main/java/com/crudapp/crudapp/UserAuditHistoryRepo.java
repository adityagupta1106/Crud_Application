package com.crudapp.crudapp;

import com.crudapp.crudapp.entities.UserAuditHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAuditHistoryRepo extends JpaRepository <UserAuditHistory,Long> {

}
