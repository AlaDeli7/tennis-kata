package com.elcodeur;

import com.elcodeur.Exception.InvalidGameSequenceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.List;

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
    void startGame_should_return_correct_game_winner() throws InvalidGameSequenceException {
        //Given
        List<List<String>> gameSequence = List.of(List.of("P1", "P1", "P2", "P1", "P1"));
        String expectedWinner = "Player1";

        //When
        game.startGame(gameSequence);

        //then
        assertEquals(expectedWinner, game.getGameWinner().orElseGet(Player::new).getName());
    }

    @Test
    void startGame_should_return_correct_match_winner() throws InvalidGameSequenceException {
        //Given
        List<List<String>> gameSequence = List.of(
                List.of("P1", "P1", "P2", "P1", "P1"),
                List.of("P1", "P1", "P2", "P1", "P1"),
                List.of("P1", "P1", "P2", "P1", "P1"),
                List.of("P1", "P1", "P2", "P1", "P1"),
                List.of("P1", "P1", "P2", "P1", "P1"),
                List.of("P1", "P1", "P2", "P1", "P1"));
        String expectedWinner = "Player1";

        //When
        game.startGame(gameSequence);

        //then
        assertEquals(expectedWinner, game.getMatchWinner().orElseGet(Player::new).getName());
    }

    @Test
    void startGame_should_return_correct_match_winner_after_tieBreak() throws InvalidGameSequenceException {
        //Given
        List<List<String>> gameSequence = List.of(
                List.of("P1", "P1", "P2", "P1", "P1"),
                List.of("P1", "P1", "P2", "P1", "P1"),
                List.of("P1", "P1", "P2", "P1", "P1"),
                List.of("P1", "P1", "P2", "P1", "P1"),
                List.of("P1", "P1", "P2", "P1", "P1"),

                List.of("P2", "P1", "P2", "P2", "P2"),
                List.of("P2", "P1", "P2", "P2", "P2"),
                List.of("P2", "P1", "P2", "P2", "P2"),
                List.of("P2", "P1", "P2", "P2", "P2"),
                List.of("P2", "P1", "P2", "P2", "P2"),

                List.of("P2", "P2", "P1", "P2", "P2"),
                List.of("P1", "P1", "P2", "P1", "P1"),

                List.of("P2", "P2", "P2", "P2", "P1", "P2", "P2", "P2"));
        String expectedWinner = "Player2";

        //When
        game.startGame(gameSequence);

        //then
        assertEquals(expectedWinner, game.getMatchWinner().orElseGet(Player::new).getName());
    }

    @Test
    void startGame_should_return_correct_winner_when_deuce() throws InvalidGameSequenceException {
        //Given
        List<List<String>> gameSequence = List.of(List.of("P1", "P1", "P2", "P1", "P2", "P2", "P2", "P1", "P1", "P1"));
        String expectedWinner = "Player1";

        //When
        game.startGame(gameSequence);

        //then
        assertEquals(expectedWinner, game.getGameWinner().orElseGet(Player::new).getName());
    }

    @Test
    void startGame_should_throw_InvalidGameExceptionSequence_when_sequence_empty() {
        //Given
        List<List<String>> gameSequence = null;

        //When
        Executable tennisGame = () -> game.startGame(gameSequence);

        //then
        assertThrows(InvalidGameSequenceException.class, tennisGame);
    }

    @Test
    void startGame_should_throw_InvalidGameExceptionSequence_when_invalid_sequence() {
        //Given
        List<List<String>> gameSequence = List.of(List.of("P1", "P1", "P2", "P1", "P2", "P2", "P2", "P1", "P1", "P1", "P1", "P2"));

        //When
        Executable tennisGame = () -> game.startGame(gameSequence);

        //then
        assertThrows(InvalidGameSequenceException.class, tennisGame);
    }
}