package com.crud.tasks.service;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloList;
import com.crud.tasks.domain.TrelloListDto;
import com.crud.tasks.mapper.TrelloMapper;
import com.crud.tasks.trello.client.TrelloClient;
import com.crud.tasks.trello.validator.TrelloValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrelloServiceTest {

    @InjectMocks
    private TrelloService trelloService;

    @Mock
    private TrelloValidator trelloValidator;

    @Mock
    private TrelloMapper trelloMapper;

    @Mock
    private TrelloClient trelloClient;

    @Test
    public void shouldFetchEmptyList() {
        //Given
        when(trelloClient.getTrelloBoards()).thenReturn(new ArrayList<>());
        //When
        List<TrelloBoardDto> trelloBoardDtos = trelloService.fetchTrelloBoards();
        //Then
        assertEquals(0, trelloBoardDtos.size());
        assertNotNull(trelloBoardDtos);

    }

    @Test
    public void shouldFetchTrelloBoards() {
        //Given
        List<TrelloListDto> trelloLists = Arrays.asList
                (new TrelloListDto("1", "my_list", false));
        List<TrelloBoardDto> trelloBoards = Arrays.asList
                (new TrelloBoardDto("1", "my_task", trelloLists));
        when(trelloClient.getTrelloBoards()).thenReturn(trelloBoards);
        //When
        List<TrelloBoardDto> trelloBoardDtos = trelloService.fetchTrelloBoards();
        //Then
        assertNotNull(trelloBoardDtos);
        assertEquals(1, trelloBoardDtos.size());
        assertEquals("my_task", trelloBoardDtos.get(0).getName());
    }

}