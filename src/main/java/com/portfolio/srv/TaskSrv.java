package com.portfolio.srv;

import com.portfolio.api.TaskApi;
import com.portfolio.api.exceptions.RepeatedTaskException;
import com.portfolio.api.exceptions.TaskNotFoundException;
import com.portfolio.api.models.Task;
import com.portfolio.dao.TaskDao;
import com.portfolio.repositories.TaskRepository;
import com.portfolio.srv.utils.TaskMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Log4j2
@Transactional      // If the operation fails it is revoked without doing anything
public class TaskSrv implements TaskApi {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private static final String TASK_ID = "Task with id: ";
    private static final String NOT_FOUND = " couldn't be found";
    private static final String LOG_ERROR = "Task with id: {} couldn't be found";

    @Override
    public Stream<Task> getTasks() {
        log.info("Get tasks request..");
        return taskRepository.findAll()
                .stream()
                .map(taskMapper::toTaskDto);
    }

    @Override
    public Task getTaskById(String idTask) {
        log.info("Get task by id request...");
        final TaskDao taskDao = taskRepository.findById(idTask)
                .orElseThrow(() -> new TaskNotFoundException(TASK_ID + idTask + NOT_FOUND));
        return taskMapper.toTaskDto(taskDao);
    }

    @Override
    public void updateTask(Task updatedTask) {
        log.info("Update task request...");
        final String idTask = updatedTask.getIdTask();
        if (checkExistence(idTask)) {
            TaskDao taskDao = taskMapper.toTaskDao(updatedTask);
            taskRepository.save(taskDao);
        } else {
            notFoundException(idTask);
        }
    }

    @Override
    public void removeTask(String idTask) {
        log.info("Remove task request...");
        if (checkExistence(idTask)) {
            taskRepository.deleteById(idTask);
        } else {
            notFoundException(idTask);
        }
    }

    @Override
    public void createTask(Task task) {
        log.info("Create task request...");
        if (taskRepository.existsByTitle(task.getTitle())) {
            log.info("Another task with the same title already exists");
            throw new RepeatedTaskException("Another task with the same title already exists");
        } else {
            taskRepository.save(taskMapper.toTaskDao(task));
        }
    }

    private static void notFoundException(final String idTask) {
        log.error(LOG_ERROR, idTask);
        throw new TaskNotFoundException(TASK_ID + idTask + NOT_FOUND);
    }

    private boolean checkExistence(final String idTask) {
        return taskRepository.existsById(idTask);
    }
}
