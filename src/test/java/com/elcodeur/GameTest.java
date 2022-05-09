package com.elcodeur;

import com.elcodeur.Exception.InvalidGameExceptionSequence;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    private Game game;

    @BeforeEach
    void setUp() {
        Player player1 = new Player("Player1");
        Player player2 = new Player("Player2");
        game = new Game(player1, player2);
    }

    @Test
    void startGame_should_return_correct_winner() throws InvalidGameExceptionSequence {
        //Given
        String[] gameSequence = {"P1", "P1", "P2", "P1", "P1"};
        String expectedWinner = "Player1";

        //When
        game.startGame(gameSequence);

        //then
        assertEquals(expectedWinner, game.getGameWinner().get().getName());
    }

    @Test
    void startGame_should_return_correct_winner_when_deuce() throws InvalidGameExceptionSequence {
        //Given
        String[] gameSequence = {"P1", "P1", "P2", "P1", "P2", "P2", "P2", "P1", "P1", "P1"};
        String expectedWinner = "Player1";

        //When
        game.startGame(gameSequence);

        //then
        assertEquals(expectedWinner, game.getGameWinner().get().getName());
    }

    @Test
    void startGame_should_throw_InvalidGameExceptionSequence_when_sequence_empty() {
        //Given
        String[] gameSequence = {};

        //When
        Executable tennisGame = () -> game.startGame(gameSequence);

        //then
        assertThrows(InvalidGameExceptionSequence.class, tennisGame);
    }

    @Test
    void startGame_should_throw_InvalidGameExceptionSequence_when_invalid_sequence() {
        //Given
        String[] gameSequence = {"P1", "P1", "P2", "P1", "P2", "P2", "P2", "P1", "P1", "P1", "P1", "P2"};

        //When
        Executable tennisGame = () -> game.startGame(gameSequence);

        //then
        assertThrows(InvalidGameExceptionSequence.class, tennisGame);
    }
}