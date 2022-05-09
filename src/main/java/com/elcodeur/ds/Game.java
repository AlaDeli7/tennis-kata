package com.elcodeur.ds;

import com.elcodeur.Exception.InvalidGameSequenceException;
import com.elcodeur.ds.Player;
import com.elcodeur.rules.TennisRulesInt;
import com.elcodeur.rules.impl.TennisRulesImpl;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Optional;

@Getter @Setter
@NoArgsConstructor
public class Game {
    private Player player1;
    private Player player2;
    private Optional<Player> gameWinner;
    private Optional<Player> matchWinner;
    private int currentGame;
    private boolean isTieBreak;

    private TennisRulesInt tennisRules;

    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        gameWinner = Optional.empty();
        matchWinner = Optional.empty();
        tennisRules = new TennisRulesImpl();
    }

    public void incrementCurrentGame() {
        currentGame += 1;
    }

    /**
     * start the game based on list of sequence of points winner
     * Example of sequence : {"P1", "P1", "P2", "P1", "P1"}
     * @param gameSets
     * @throws InvalidGameSequenceException
     * This exception is thrown in case a player won the game
     * and the game is still on
     */
    public void startGame(List<List<String>> gameSets) throws InvalidGameSequenceException {
        if (gameSets == null) {
            throw new InvalidGameSequenceException("Invalid game sequence");
        }
        for (List<String> gameSet : gameSets) {
            if (matchWinner.isPresent()) {
                throw new InvalidGameSequenceException("Invalid game sequence : Match winner already decided");
            }
            gameWinner = Optional.empty();
            for (String pointSequence : gameSet) {
                updateGameScore(pointSequence);
            }
            gameWinner.ifPresent(s -> {
                    System.out.println(gameWinner.get().getName() + " win the game number "+currentGame+ " of the Set");
                    System.out.println("######################################");
                    });
        }
    }

    private void updateGameScore(String pointSequence) throws InvalidGameSequenceException {
        if (gameWinner.isPresent()) {
            throw new InvalidGameSequenceException("Invalid game sequence : Game winner already decided");
        }
        if ("P1".equalsIgnoreCase(pointSequence)) {
            applyGameRules(player1, player2);
        } else {
            applyGameRules(player2, player1);
        }
    }

    private void applyGameRules(Player pointWinner, Player otherPlayer) {
        if (isTieBreak()) {
            tennisRules.updateGameScoreWithTieBreakRule(pointWinner, otherPlayer, this);
        } else {
            tennisRules.updateGameScoreWithRegularRule(pointWinner, otherPlayer, this);
        }
        displayGameScore(pointWinner);
    }

    private void displayGameScore(Player pointWinner) {
        System.out.println(pointWinner.getName() + " wins 1 point");
        System.out.println("Player 1 Game Score : " + player1.getGameScore());
        System.out.println("Player 2 Game score : " + player2.getGameScore());
        System.out.println("Player 1 Set Score : " + player1.getGameWins());
        System.out.println("Player 2 Set Score : " + player2.getGameWins());
        if (isTieBreak()) {
            System.out.println("Player 1 TieBreak Score : " + player1.getTieBreakScore());
            System.out.println("Player 2 TieBreak Score : " + player2.getTieBreakScore());
        }
        System.out.println("######################################");
    }
}
