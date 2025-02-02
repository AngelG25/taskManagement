package com.portfolio.rest;

import com.portfolio.api.TaskApi;
import com.portfolio.api.models.Task;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Stream;

@RestController
@AllArgsConstructor
public class TaskRest {

    private final TaskApi taskApi;

    @GetMapping("/getTasks")
    public Stream<Task> getTasks() {
        return taskApi.getTasks();
    }

    @GetMapping("/getTaskById/{idTask}")
    public Task getTaskById(@PathVariable("idTask") String idTask) {
        return taskApi.getTaskById(idTask);
    }

    @PostMapping("/createTask")
    public void createTask(@RequestBody Task task) {
        taskApi.updateTask(task);
    }

    @PostMapping("/updateTask")
    public void updateTask(@RequestBody Task task) {
        taskApi.updateTask(task);
    }

    @PostMapping("/deleteTask/{idTask}")
    public void removeTask(@PathVariable("idTask") String idTask) {
        taskApi.removeTask(idTask);
    }
}
