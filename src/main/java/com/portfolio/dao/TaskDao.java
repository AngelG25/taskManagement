package com.portfolio.dao;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Entity
@Table(name = "tasks")
public class TaskDao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "uuid", nullable = false)
    String uuidTask;

    @Column(name = "creation_date", updatable = false)
    @CreationTimestamp
    private Instant creationDate;

    @Column(name = "update_date", updatable = false)
    @UpdateTimestamp
    private Instant updateDate;

    @Column(name = "end_date", updatable = false)
    private Instant endDate;

    @Column(name = "description", updatable = false)
    private String description;

    @Column(name = "title", updatable = false)
    private String title;

    @Column(name = "priority", updatable = false)
    private String priority;

}
