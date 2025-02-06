package com.portfolio.srv;


import com.portfolio.api.exceptions.TaskNotFoundException;
import com.portfolio.api.models.Task;
import com.portfolio.dao.TaskDao;
import com.portfolio.repositories.TaskRepository;
import com.portfolio.srv.utils.ObjectsCreator;
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
    private static final ObjectsCreator objectsCreator = new ObjectsCreator();

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
        final TaskDao taskDao = objectsCreator.taskDaoCreation();
        final Task expectedTask = objectsCreator.taskCreation();

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
        Task task = objectsCreator.taskCreation();
        task = task.toBuilder().idTask(UUID).build();
        TaskDao savedTask = objectsCreator.taskDaoCreation();

        when(taskRepository.existsById(UUID)).thenReturn(true);
        when(taskMapper.toTaskDao(task)).thenReturn(savedTask);

        taskSrv.updateTask(task);

        verify(taskRepository, times(1)).existsById(UUID);
        verify(taskRepository, times(1)).save(savedTask);
        verify(taskMapper, only()).toTaskDao(task);
    }

    @Test
    void testUpdateNonExistingTask() {
        Task task = objectsCreator.taskCreation();

        when(taskRepository.existsById(any())).thenReturn(false);

        assertThrows(TaskNotFoundException.class, () -> taskSrv.updateTask(task));

        verify(taskRepository, times(1)).existsById(any());
    }
}
