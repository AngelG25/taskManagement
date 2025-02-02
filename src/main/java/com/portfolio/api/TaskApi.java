package com.portfolio.api;

import com.portfolio.api.exceptions.TaskNotFoundException;
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
     * @param idTask the id of the task you are looking for
     * @return the task with the id indicated.
     * @throws TaskNotFoundException if the task cannot be found
     */
    Task getTaskById(String idTask);

    /**
     * Updates a task with the information in the body.
     *
     * @param task the task with the modified fields.
     * @throws TaskNotFoundException if the task cannot be found
     */
    void updateTask(Task task);

    /**
     * Deletes a task by their ID.
     *
     * @param idTask the UUID of the task to delete.
     * @throws TaskNotFoundException if the task cannot be found
     */
    void removeTask(String idTask);

    /**
     * Creates a task with the information in the body.
     *
     * @param task the task that will be created.
     */

    void createTask(Task task);
}
