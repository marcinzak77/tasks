package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.repository.TaskRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DbServiceTest {
    @Autowired
    DbService dbService;
    @Autowired
    TaskMapper taskMapper;


    @Test
    public void shouldSaveTask() {
        //Given
        TaskDto taskDto = new TaskDto(1l, "test_title", "test_content");
        Task task = taskMapper.mapToTask(taskDto);

        //When
        dbService.saveTask(task);
        Long id = task.getId();
        Task resultTask = dbService.getTask(id).orElse(null);
        //Then
        assertEquals(1, dbService.getAllTask().size());
        assertNotNull(dbService.getTask(id));
        assertEquals("test_title", dbService.getTask(id).get().getTitle());
        assertEquals("test_content", dbService.getTask(id).get().getContent());
        System.out.println(dbService.getAllTask().get(0).getId() + " " + id);

        //Cleanup
        try {
            dbService.deleteTask(id);
        } catch (Exception e) {
            //do nothing
        }

    }

}