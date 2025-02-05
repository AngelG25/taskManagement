package com.portfolio.rest;

import com.portfolio.api.TaskApi;
import com.portfolio.api.models.Task;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Stream;

@RestController
@AllArgsConstructor
public class TaskRest {

    private final TaskApi taskApi;
    private static final String TASK_EXAMPLE = "{\n" +
            "  \"end_date\": \"2025-02-02T18:34:18.903Z\",\n" +
            "  \"description\": \"This is a sample description for the task.\",\n" +
            "  \"title\": \"Sample Task\",\n" +
            "  \"priority\": \"High\"\n" +
            "}\n";

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
    public Task getTaskById(@Parameter(description = "ID from the task to be eliminated",
            example = "123e4567-e89b-12d3-a456-426614174000") @PathVariable("idTask") String idTask) {
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
    public void createTask(@io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Objet Task that will be created",
            required = true,
            content = @Content(mediaType = "application/json", examples = @ExampleObject(value = TASK_EXAMPLE)))@RequestBody Task task) {
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
    public void updateTask(@io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Objet Task with the fields to update",
            required = true,
            content = @Content(mediaType = "application/json", examples = @ExampleObject(value = TASK_EXAMPLE))) @RequestBody Task task) {
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
