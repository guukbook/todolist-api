package com.example.todolist.mapper;

import com.example.todolist.dto.TodoDTO;
import com.example.todolist.entity.TaskEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper

public interface TodoMapper {
    TodoMapper INSTANCE = Mappers.getMapper(TodoMapper.class);

    TodoDTO taskToDto(TaskEntity task);
    TaskEntity dtoToTask(TodoDTO dto);
    
}
