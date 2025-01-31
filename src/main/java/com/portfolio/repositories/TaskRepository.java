package com.portfolio.repositories;

import com.portfolio.api.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, String> {

    // Using JpaRepository creates a CRUD automatically for the TaskRepository and is perfect
    // for an application using Jakarta JPA with Hibernate
}
