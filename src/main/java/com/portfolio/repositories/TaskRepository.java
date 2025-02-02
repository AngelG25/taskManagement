package com.portfolio.repositories;

import com.portfolio.dao.TaskDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<TaskDao, String> {

    // Using JpaRepository creates a CRUD automatically for the TaskRepository and is perfect
    // for an application using Jakarta JPA with Hibernate

}
