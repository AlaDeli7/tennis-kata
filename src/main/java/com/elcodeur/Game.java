package com.elcodeur;

import com.elcodeur.Exception.InvalidGameExceptionSequence;
import com.elcodeur.enums.Score;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Optional;

@Getter @Setter
@NoArgsConstructor
public class Game {
    private Player player1;
    private Player player2;
    private Optional<Player> gameWinner;

    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        gameWinner = Optional.empty();
    }

    /**
     * start the game based on sequence of points winner
     * Example : {"P1", "P1", "P2", "P1", "P1"}
     * @param gameSequence sequence of points winner
     * @throws InvalidGameExceptionSequence
     * This exception is thrown in case a player won the game
     * and the game is still on
     */
    public void startGame(String[] gameSequence) throws InvalidGameExceptionSequence {
        for (String pointSequence : gameSequence) {
            //
            if (gameWinner.isPresent()) {
                throw new InvalidGameExceptionSequence("Invalid game sequence : Game must end");
            }
            if (pointSequence.equalsIgnoreCase("P1")) {
                updateScore(player1, player2);
            } else {
                updateScore(player2, player1);
            }
        }
        gameWinner.ifPresent(s -> System.out.println(gameWinner.get().getName() + " win the game"));
    }

    private void updateScore(Player pointWinner, Player otherPlayer) {
        if (pointWinner.getScore().equals(Score.ADV)
                || (pointWinner.getScore().equals(Score.FORTY)
                    && !otherPlayer.getScore().equals(Score.FORTY)
                    && !otherPlayer.getScore().equals(Score.ADV))) {
            setGameWinner(Optional.of(pointWinner));
            pointWinner.setScore(Score.ZERO);
            otherPlayer.setScore(Score.ZERO);
        } else if (pointWinner.getScore().equals(Score.FORTY)
                && otherPlayer.getScore().equals(Score.ADV)) {
            pointWinner.setScore(Score.DEUCE);
            otherPlayer.setScore(Score.DEUCE);
        } else if (pointWinner.getScore().equals(Score.DEUCE)
                ||
                (pointWinner.getScore().equals(Score.FORTY)
                && otherPlayer.getScore().equals(Score.FORTY))) {
            pointWinner.setScore(Score.ADV);
            otherPlayer.setScore(Score.FORTY);
        } else if (pointWinner.getScore().equals(Score.ZERO)) {
            pointWinner.setScore(Score.FIFTEEN);
        } else if (pointWinner.getScore().equals(Score.FIFTEEN)) {
            pointWinner.setScore(Score.THIRTY);
        } else if (pointWinner.getScore().equals(Score.THIRTY)) {
            pointWinner.setScore(Score.FORTY);
        }
        displayGameScore(pointWinner);
    }

    private void displayGameScore(Player pointWinner) {
        System.out.println(pointWinner.getName() + " wins 1 point");
        System.out.println("Player 1 score : " + resolveEnumScore(player1.getScore()));
        System.out.println("Player 2 score : " + resolveEnumScore(player2.getScore()));
        System.out.println("######################################");
    }

    private String resolveEnumScore(Score enumScore) {
        if (enumScore.equals(Score.ADV) || enumScore.equals(Score.DEUCE)) {
            return enumScore.name();
        } else {
            return enumScore.toString();
        }
    }
}
