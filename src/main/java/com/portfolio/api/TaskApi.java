package com.portfolio.api;

import com.portfolio.api.models.Task;

import java.util.stream.Stream;

public interface TaskApi {

    /**
     * Fetches all tasks as a stream.
     *
     * @return a stream of tasks.
     */
    Stream<Task> getTasks();

    /**
     * Fetches a task by their ID.
     *
     * @return the task with the id indicated.
     */
    Task getTaskById();

    /**
     * Updates a task with the information in the body.
     *
     * @param task the task with the modified fields.
     * @return true if the task was successfully updated.
     */
    Boolean updateTask(Task task);

    /**
     * Deletes a task by their ID.
     *
     * @param taskId the UUID of the task to delete.
     * @return true if the task was successfully deleted, or false if the task was not found.
     */
    Boolean removeTask(String taskId);
}
