package com.portfolio.rest;

import com.portfolio.api.TaskApi;
import com.portfolio.api.models.Task;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import lombok.AllArgsConstructor;

import java.util.stream.Stream;

@AllArgsConstructor
public class TaskRest {

    private final TaskApi taskApi;

    @GET
    @Path("/getTasks")
    @Produces(MediaType.APPLICATION_JSON)
    public Stream<Task> getTasks() {
        return taskApi.getTasks();
    }

    @GET
    @Path("/getTaskById/{idTask}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    public Task getTaskById(@PathParam("idTask") String idTask) {
        return taskApi.getTaskById(idTask);
    }

    @POST
    @Path("/updateTask")
    @Consumes(MediaType.APPLICATION_JSON)
    public Boolean updateTask(Task task) {
        return taskApi.updateTask(task);
    }

    @POST
    @Path("/deleteTask/{idTask}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Boolean removeTask(@PathParam("idTask") String idTask) {
        return taskApi.removeTask(idTask);
    }
}
