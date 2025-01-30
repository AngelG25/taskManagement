package com.portfolio.rest;

import com.portfolio.api.TaskApi;
import com.portfolio.api.models.Task;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.stream.Stream;

@Tag(name = "Task")
public class TaskRest implements TaskApi {

    @GetMapping("/getTasks")
    public Stream<Task> getTasks() {
        return Stream.empty();
    }

    @GetMapping("/getTaskById/{taskId}")
    public Task getTaskById(String taskId) {
        return null;
    }

    @PostMapping("/updateTask")
    public Boolean updateTask(Task task) {
        return null;
    }

    @PostMapping("/deleteTask")
    public Boolean removeTask(String taskId) {
        return null;
    }
}
