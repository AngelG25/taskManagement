package com.portfolio.rest;

import com.portfolio.api.TaskApi;
import com.portfolio.api.models.Task;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.stream.Stream;

public class TaskRest implements TaskApi {

    @GET
    @Path("/getTasks")
    @Produces(MediaType.APPLICATION_JSON)       // JAKARTA
    public Stream<Task> getTasks() {
        return Stream.empty();
    }

    @GET
    @Path("/getTaskById/{idTask}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    public Task getTaskById(@PathParam("idTask") String idTask) {
        return null;
    }

    @POST
    @Path("/updateTask")
    @Consumes(MediaType.APPLICATION_JSON)
    public Boolean updateTask(Task task) {
        return null;
    }

    @POST
    @Path("/deleteTask/{idTask}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Boolean removeTask(@PathParam("idTask") String idTask) {
        return null;
    }
}
