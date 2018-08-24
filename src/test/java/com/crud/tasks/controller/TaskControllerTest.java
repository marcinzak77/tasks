package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskMapper taskMapper;

    @MockBean
    private DbService dbService;

    @Test
    public void shouldGetTasksList() throws Exception {
        //Given
        List<TaskDto> taskDtoList = Arrays.asList(new TaskDto(1L, "test", "test"));
        List<Task> taskList = new ArrayList<>();
        when(taskMapper.mapToTaskDtoList(any())).thenReturn(taskDtoList);
        when(dbService.getAllTask()).thenReturn(taskList);
        //When & Then
        mockMvc.perform(get("/v1/task/getTasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].title", is("test")))
                .andExpect(jsonPath("$[0].content", is("test")));
    }

    @Test
    public void shouldGetTaskById() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(1L, "test", "test");
        Task task = new Task();
        when(taskMapper.mapToTaskDto(any(Task.class))).thenReturn(taskDto);
        when(dbService.getTask(any(Long.class))).thenReturn(Optional.of(task));


        //When & Then
        mockMvc.perform(get("/v1/task/getTask?taskId=1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("test")))
                .andExpect(jsonPath("$.content", is("test")));


    }

    @Test
    public void shouldDeleteTask() throws Exception {
        //Given

        //When @ Then
        mockMvc.perform(delete("/v1/task/deleteTask?taskId=1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldUpdateTask() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(1L, "test", "test");
        Task task = new Task(2L, "test 2", "test 2");
        when(dbService.saveTask(any(Task.class))).thenReturn(task);
        when(taskMapper.mapToTask(taskDto)).thenReturn(task);
        when(taskMapper.mapToTaskDto(any(Task.class))).thenCallRealMethod();
        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);
        //When & Then
        mockMvc.perform(put("/v1/task/updateTask").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(2)))
                .andExpect(jsonPath("$.title", is("test 2")))
                .andExpect(jsonPath("$.content", is("test 2")));
    }

    @Test
    public void shouldCreateTask() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(1L, "test", "test");
        Task task = new Task(2L, "test 2", "test 2");
        when(dbService.saveTask(any(Task.class))).thenReturn(task);
        when(taskMapper.mapToTask(any(TaskDto.class))).thenCallRealMethod();
        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);
        //When & Then
        mockMvc.perform(post("/v1/task/createTask").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());

    }

}