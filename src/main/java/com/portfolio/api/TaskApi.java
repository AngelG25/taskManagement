package com.portfolio.api;

import com.portfolio.api.models.Task;

import java.util.stream.Stream;
import com.portfolio.api.exceptions.TaskNotFoundException;

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
     * @param idTask the id of the task you are looking for
     * @return the task with the id indicated.
     * @throws TaskNotFoundException if the task cannot be found
     */
    Task getTaskById(String idTask);

    /**
     * Updates a task with the information in the body.
     *
     * @param task the task with the modified fields.
     * @return true if the task was successfully updated.
     * @throws TaskNotFoundException if the task cannot be found
     */
    Boolean updateTask(Task task);

    /**
     * Deletes a task by their ID.
     *
     * @param idTask the UUID of the task to delete.
     * @return true if the task was successfully deleted, or false if the task was not found.
     * @throws TaskNotFoundException if the task cannot be found
     */
    Boolean removeTask(String idTask);
}
