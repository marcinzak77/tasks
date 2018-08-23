package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class TaskMapperTest {
    @InjectMocks
    TaskMapper taskMapper;

    @Test
    public void mapToTask() {
        //Given
        TaskDto taskDto = new TaskDto(10, "title_1", "task content");
        //When
        Task task = taskMapper.mapToTask(taskDto);
        //Then
        assertEquals("title_1", task.getTitle());
        assertEquals("task content", task.getContent());
    }

    @Test
    public void mapToTaskDto() {
        //Given
        Task task = new Task(1l, "title_1", "task content");
        //When
        TaskDto taskDto = taskMapper.mapToTaskDto(task);
        //Then
        assertEquals(1L, taskDto.getId());
        assertEquals("title_1", task.getTitle());
        assertEquals("task content", taskDto.getContent());
    }

    @Test
    public void mapToTaskDtoList() {
        //Given
        List<Task> taskList = Arrays.asList(new Task(1L, "title_1", "task content"));
        //When
        List<TaskDto> taskDtos = taskMapper.mapToTaskDtoList(taskList);
        //Then
        assertEquals(1, taskDtos.size());
        assertEquals(1L, taskDtos.get(0).getId());
        assertEquals("title_1", taskDtos.get(0).getTitle());
        assertEquals("task content", taskDtos.get(0).getContent());
    }
}