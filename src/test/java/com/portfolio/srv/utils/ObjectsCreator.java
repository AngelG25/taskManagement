package com.portfolio.srv.utils;

import com.portfolio.api.models.Task;
import com.portfolio.dao.TaskDao;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@NoArgsConstructor
public class ObjectsCreator {

    private static final String UUID = "123e4567-e89b-12d3-a456-426614174000";

    public Task taskCreation() {
        return Task.builder()
                .title("title task")
                .priority("high")
                .description("description")
                .endDate(Instant.now().plus(3, ChronoUnit.DAYS))
                .build();
    }

    public TaskDao taskDaoCreation() {
        TaskDao taskDao = new TaskDao();
        taskDao.setTitle("title");
        taskDao.setPriority("high");
        taskDao.setDescription("description");
        taskDao.setEndDate(Instant.now().plus(3, ChronoUnit.DAYS));
        return taskDao;
    }
}
