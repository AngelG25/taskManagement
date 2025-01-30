package com.portfolio.srv;

import com.portfolio.api.TaskApi;
import com.portfolio.api.models.Task;

import java.util.stream.Stream;

public class TaskSrv implements TaskApi {

    @Override
    public Stream<Task> getTasks() {
        return Stream.empty();
    }

    @Override
    public Task getTaskById(String idTask) {
        return null;
    }

    @Override
    public Boolean updateTask(Task task) {
        return null;
    }

    @Override
    public Boolean removeTask(String idTask) {
        return null;
    }
}
