package com.crud.tasks.mapper;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloList;
import com.crud.tasks.domain.TrelloListDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class TrelloMapperTest {
    @InjectMocks
    TrelloMapper trelloMapper;



    @Test
    public void mapToBoards() {
        //Given
        TrelloBoardDto trelloBoardDto = new TrelloBoardDto("test_id", "test_board", new ArrayList<>());
        List<TrelloBoardDto> trelloBoardDtoList = new ArrayList<>(Arrays.asList(trelloBoardDto));
        //When
        List<TrelloBoard> trelloBoardList = trelloMapper.mapToBoards(trelloBoardDtoList);
        //Then
        assertEquals(1, trelloBoardList.size());
        assertEquals("test_id", trelloBoardList.get(0).getId());
    }

    @Test
    public void mapToBoardsDto() {
        //Given
        List<TrelloBoard> trelloBoardList = new ArrayList<>(Arrays.
                asList(new TrelloBoard("test_id", "test_board", new ArrayList<>())));

        //When
        List<TrelloBoardDto> trelloBoardDtoList = trelloMapper.mapToBoardsDto(trelloBoardList);

        //Then
        assertEquals(1, trelloBoardDtoList.size());
        assertEquals("test_id", trelloBoardDtoList.get(0).getId());
    }

    @Test
    public void mapToList() {
        //Given
        TrelloListDto trelloListDto = new TrelloListDto("test_id", "test_name", true);
        List<TrelloListDto> trelloListDtoList = new ArrayList<>(Arrays.
                asList(trelloListDto));

        //When
        List<TrelloList> trelloLists = trelloMapper.mapToList(trelloListDtoList);

        //Then
        assertEquals(1, trelloLists.size());
        assertEquals("test_id", trelloLists.get(0).getId());
    }

    @Test
    public void mapToListDto() {
        //Given

        //When

        //Then
    }

    @Test
    public void mapToCardDto() {
        //Given

        //When

        //Then
    }

    @Test
    public void mapToCard() {
        //Given

        //When

        //Then
    }
}