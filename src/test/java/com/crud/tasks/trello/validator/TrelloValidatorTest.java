package com.crud.tasks.trello.validator;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloCard;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class TrelloValidatorTest {
    @InjectMocks
    private TrelloValidator trelloValidator;

    @Test
    public void shouldFilterTrelloBoards() {
        //Given
        List<TrelloBoard> trelloBoards = Arrays.asList(new TrelloBoard("1", "test", new ArrayList<>()));
        //When
        List<TrelloBoard> results = trelloValidator.validateTrelloBoards(trelloBoards);
        //Then
        assertEquals(1, trelloBoards.size());
        assertEquals(0, results.size());

    }

    @Test
    public void shouldNotFilterTrelloBoards() {
        //Given
        List<TrelloBoard> trelloBoards = Arrays.asList(new TrelloBoard("1", "Very good name", new ArrayList<>()));
        //When
        List<TrelloBoard> results = trelloValidator.validateTrelloBoards(trelloBoards);
        //Then
        assertEquals(1, trelloBoards.size());
        assertEquals(1, results.size());
    }

    @Test
    public void shouldDisplayTestingMessage() {
        //Given
        TrelloCard trelloCard = new TrelloCard("test", "test", "test", "test");
        //When
        trelloValidator.validateCard(trelloCard);
        //Then
    }

    @Test
    public void shouldDisplayEverythingIsFine() {
        //Given
        TrelloCard trelloCard = new TrelloCard("good name", "good description", "top", "12");
        //When
        trelloValidator.validateCard(trelloCard);
        //Then
    }
}