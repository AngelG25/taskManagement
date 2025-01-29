package com.portfolio.api;

import com.portfolio.api.models.Task;

import java.util.stream.Stream;

public interface TaskApi {

    Stream<Task> getTasks();

    Task getTaskById();

    Boolean updateTask(Task task);

    Boolean removeTask(String taskId);
}
