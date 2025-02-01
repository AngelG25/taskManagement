package com.portfolio.srv;

import com.portfolio.api.TaskApi;
import com.portfolio.api.exceptions.TaskNotFoundException;
import com.portfolio.api.models.Task;
import com.portfolio.repositories.TaskRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
@AllArgsConstructor
@Log4j2
public class TaskSrv implements TaskApi {

    private final TaskRepository taskRepository;
    private static final String TASK_ID = "Task with id: ";
    private static final String NOT_FOUND = "couldn't be found";
    private static final String LOG_ERROR = "Task with id: {} couldn't be found";

    // TODO FIX all the repository to use TaskDao instead of Task

    @Override
    public Stream<Task> getTasks() {
        log.info("Get tasks request..");
        return taskRepository.findAll().stream();
    }

    @Override
    public Task getTaskById(String idTask) {
        log.info("Get task by id request...");
        return taskRepository.findById(idTask)
                .orElseThrow(() -> new TaskNotFoundException(TASK_ID + idTask + NOT_FOUND));
    }

    @Override
    public void updateTask(Task updatedTask) {
        log.info("Update task request...");
        final String idTask = updatedTask.getIdTask();
        if (checkExistence(idTask)) {
            taskRepository.save(updatedTask);
        } else {
            throwException(idTask);
        }
    }

    @Override
    public void removeTask(String idTask) {
        log.info("Remove task request...");
        if (checkExistence(idTask)) {
            taskRepository.deleteById(idTask);
        } else {
            throwException(idTask);
        }
    }

    private static void throwException(final String idTask) {
        log.error(LOG_ERROR, idTask);
        throw new TaskNotFoundException(TASK_ID + idTask + NOT_FOUND);
    }

    private boolean checkExistence(final String idTask) {
        return taskRepository.existsById(idTask);
    }
}
