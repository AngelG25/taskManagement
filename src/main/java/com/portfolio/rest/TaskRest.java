package com.portfolio.rest;

import com.portfolio.api.TaskApi;
import com.portfolio.api.models.Task;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Stream;

@RestController
@AllArgsConstructor
public class TaskRest {

    private final TaskApi taskApi;

    @Operation(
            summary = "Gets all the tasks",
            description = "Gets all the tasks from the database PostgresSQL"
    )
    @ApiResponse(responseCode = "200", description = "Tasks returned correctly")
    @GetMapping("/getTasks")
    public Stream<Task> getTasks() {
        return taskApi.getTasks();
    }

    @Operation(
            summary = "Gets an specific task",
            description = "Get the task with the id indicated in the parameter"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task found correctly"),
            @ApiResponse(responseCode = "404", description = "Task not found with the id indicated"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/getTaskById/{idTask}")
    public Task getTaskById(@PathVariable("idTask") String idTask) {
        return taskApi.getTaskById(idTask);
    }

    @Operation(
            summary = "Creates a task",
            description = "Creates a task with the body loaded in the request"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task created correctly"),
            @ApiResponse(responseCode = "404", description = "Task couldn't be created"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/createTask")
    public void createTask(@RequestBody Task task) {
        taskApi.createTask(task);
    }

    @Operation(
            summary = "Updates a task",
            description = "Updates a task with the body loaded in the request"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task updated correctly"),
            @ApiResponse(responseCode = "404", description = "Task not found with the id indicated"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping("/updateTask")
    public void updateTask(@Parameter(description = "Task with the fields updated",
            example = "123e4567-e89b-12d3-a456-426614174000")@RequestBody Task task) {
        taskApi.updateTask(task);
    }

    @Operation(
            summary = "Removes a task",
            description = "Removes an existing task from the data base using the ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task removed correctly"),
            @ApiResponse(responseCode = "404", description = "Task not found with the id indicated"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/deleteTask/{idTask}")
    public void removeTask(@Parameter(description = "ID from the task to be eliminated",
            example = "123e4567-e89b-12d3-a456-426614174000") @PathVariable("idTask") String idTask) {
        taskApi.removeTask(idTask);
    }
}
