package com.portfolio.srv;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.portfolio.api.TaskApi;
import com.portfolio.api.exceptions.TaskNotFoundException;
import com.portfolio.api.models.Task;
import com.portfolio.dao.TaskDao;
import com.portfolio.repositories.TaskRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
@AllArgsConstructor
@Log4j2
@Transactional      // If the operation fails it is revoked without doing anything
public class TaskSrv implements TaskApi {

    private final TaskRepository taskRepository;
    private static final String TASK_ID = "Task with id: ";
    private static final String NOT_FOUND = "couldn't be found";
    private static final String LOG_ERROR = "Task with id: {} couldn't be found";
    private static final ObjectMapper objectMapper = new ObjectMapper();        // TODO change objectMapper for MapStruct

    @Override
    public Stream<Task> getTasks() {
        log.info("Get tasks request..");
        return taskRepository.findAll().stream()
                .map(taskDao -> objectMapper.convertValue(taskDao, Task.class));
    }

    @Override
    public Task getTaskById(String idTask) {
        log.info("Get task by id request...");
        final TaskDao taskDao = taskRepository.findById(idTask)
                .orElseThrow(() -> new TaskNotFoundException(TASK_ID + idTask + NOT_FOUND));
        return objectMapper.convertValue(taskDao, Task.class);
    }

    @Override
    public void updateTask(Task updatedTask) {
        log.info("Update task request...");
        final String idTask = updatedTask.getIdTask();
        if (checkExistence(idTask)) {
            taskRepository.save(objectMapper.convertValue(updatedTask, TaskDao.class));
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

    @Override
    public void createTask(Task task) {
        log.info("Create task request...");
        //TODO check if the same description already exists
        taskRepository.save(objectMapper.convertValue(task, TaskDao.class));
        log.error("Task with id: {} already exists", task.getIdTask());
        throw new TaskNotFoundException(TASK_ID + task.getIdTask() + "already exists");

    }

    private static void throwException(final String idTask) {
        log.error(LOG_ERROR, idTask);
        throw new TaskNotFoundException(TASK_ID + idTask + NOT_FOUND);
    }

    private boolean checkExistence(final String idTask) {
        return taskRepository.existsById(idTask);
    }
}
