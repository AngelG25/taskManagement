package com.portfolio.srv;

import com.portfolio.api.exceptions.RepeatedTaskException;
import com.portfolio.api.exceptions.TaskNotFoundException;
import com.portfolio.api.models.Task;
import com.portfolio.dao.TaskDao;
import com.portfolio.repositories.TaskRepository;
import com.portfolio.srv.utils.TasksCreator;
import com.portfolio.srv.utils.TaskMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskSrvTest {

    private static final String UUID = "123e4567-e89b-12d3-a456-426614174000";
    private final TasksCreator tasksCreator = new TasksCreator();

    @InjectMocks
    TaskSrv taskSrv;

    @Mock
    TaskRepository taskRepository;

    @Mock
    TaskMapper taskMapper;

    @Test
    void testGetTasks() {
        assertNotNull(taskSrv.getTasks());
        verify(taskRepository, only()).findAll();
        verifyNoInteractions(taskMapper);
    }

    @Test
    void testGetTaskByIdOK() {
        final TaskDao taskDao = tasksCreator.taskDaoCreation();
        final Task expectedTask = tasksCreator.taskCreation();

        when(taskRepository.findById(UUID)).thenReturn(Optional.ofNullable(taskDao));
        when(taskMapper.toTaskDto(taskDao)).thenReturn(expectedTask);

        assertEquals(expectedTask, taskSrv.getTaskById(UUID));

        verify(taskRepository, only()).findById(UUID);
        verify(taskMapper, only()).toTaskDto(taskDao);
    }

    @Test
    void testGetTaskByIdException() {
        assertThrows(TaskNotFoundException.class, () -> taskSrv.getTaskById(UUID));

        verify(taskRepository, only()).findById(UUID);
        verifyNoInteractions(taskMapper);
    }

    @Test
    void testUpdateTaskOK() {
        Task task = tasksCreator.taskCreation();
        task = task.toBuilder().idTask(UUID).build();
        TaskDao savedTask = tasksCreator.taskDaoCreation();

        when(taskRepository.existsById(UUID)).thenReturn(true);
        when(taskMapper.toTaskDao(task)).thenReturn(savedTask);

        taskSrv.updateTask(task);

        verify(taskRepository, times(1)).existsById(UUID);
        verify(taskRepository, times(1)).save(savedTask);
        verify(taskMapper, only()).toTaskDao(task);
    }

    @Test
    void testUpdateNonExistingTask() {
        Task task = tasksCreator.taskCreation();

        when(taskRepository.existsById(any())).thenReturn(false);

        assertThrows(TaskNotFoundException.class, () -> taskSrv.updateTask(task));

        verify(taskRepository, only()).existsById(any());
    }

    @Test
    void testRemoveTask() {
        when(taskRepository.existsById(UUID)).thenReturn(true);

        taskSrv.removeTask(UUID);

        verify(taskRepository, times(1)).existsById(UUID);
        verify(taskRepository, times(1)).deleteById(UUID);
    }

    @Test
    void testRemoveNonExistingTask() {
        when(taskRepository.existsById(UUID)).thenReturn(false);

        assertThrows(TaskNotFoundException.class, () -> taskSrv.removeTask(UUID));

        verify(taskRepository, only()).existsById(UUID);
    }

    @Test
    void testCreateTask() {
        Task task = tasksCreator.taskCreation();
        TaskDao createdTask = tasksCreator.taskDaoCreation();

        when(taskRepository.existsByTitle(anyString())).thenReturn(false);
        when(taskMapper.toTaskDao(task)).thenReturn(createdTask);

        taskSrv.createTask(task);

        verify(taskRepository, times(1)).existsByTitle(anyString());
        verify(taskRepository, times(1)).save(createdTask);
        verify(taskMapper, only()).toTaskDao(task);
    }

    @Test
    void testCreateAlreadyExistingTask() {
        Task task = tasksCreator.taskCreation();

        when(taskRepository.existsByTitle(anyString())).thenReturn(true);

        assertThrows(RepeatedTaskException.class, () -> taskSrv.createTask(task));

        verify(taskRepository, only()).existsByTitle(anyString());
        verifyNoInteractions(taskMapper);
    }
}
