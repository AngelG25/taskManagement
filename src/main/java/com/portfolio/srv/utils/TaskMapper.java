package com.portfolio.srv.utils;

import com.portfolio.api.models.Task;
import com.portfolio.dao.TaskDao;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    @Mapping(target = "idTask", source = "idTask")
    TaskDao toTaskDao(Task task);

    @Mapping(target = "idTask", source = "idTask")
    Task toTaskDto(TaskDao taskDao);

}