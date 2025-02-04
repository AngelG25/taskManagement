package com.portfolio.srv.utils;

import com.portfolio.api.models.Task;
import com.portfolio.dao.TaskDao;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    TaskDao toTaskDao(Task task);

    Task toTaskDto(TaskDao taskDao);

}