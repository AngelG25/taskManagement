package com.portfolio.dao;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Entity
@NoArgsConstructor              // Necessary for the build in the get API
@Getter
@Setter
@Table(name = "tasks")
public class TaskDao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_task", nullable = false)
    String idTask;

    @Column(name = "creation_date", updatable = false)
    @CreationTimestamp
    private Instant creationDate;

    @Column(name = "update_date")
    @UpdateTimestamp
    private Instant updateDate;

    @Column(name = "end_date")
    private Instant endDate;

    @Column(name = "description")
    private String description;

    @Column(name = "title")
    private String title;

    @Column(name = "priority")
    private String priority;

}
