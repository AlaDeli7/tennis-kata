package com.elcodeur.rules.impl;

import com.elcodeur.Game;
import com.elcodeur.Player;
import com.elcodeur.enums.Score;
import com.elcodeur.rules.TennisRulesInt;

import java.util.Optional;

public class TennisRulesImpl implements TennisRulesInt {
    @Override
    public void updateGameScoreWithRegularRule(Player pointWinner, Player otherPlayer, Game game) {
        if (pointWinner.getGameScore().equals(Score.ADV)
                || (pointWinner.getGameScore().equals(Score.FORTY)
                && !otherPlayer.getGameScore().equals(Score.FORTY)
                && !otherPlayer.getGameScore().equals(Score.ADV))) {
            game.setGameWinner(Optional.of(pointWinner));
            pointWinner.setGameScore(Score.ZERO);
            otherPlayer.setGameScore(Score.ZERO);
            updateGameSetScore(pointWinner, otherPlayer, game);
        } else if (pointWinner.getGameScore().equals(Score.FORTY)
                && otherPlayer.getGameScore().equals(Score.ADV)) {
            pointWinner.setGameScore(Score.DEUCE);
            otherPlayer.setGameScore(Score.DEUCE);
        } else if (pointWinner.getGameScore().equals(Score.DEUCE)
                ||
                (pointWinner.getGameScore().equals(Score.FORTY)
                        && otherPlayer.getGameScore().equals(Score.FORTY))) {
            pointWinner.setGameScore(Score.ADV);
            otherPlayer.setGameScore(Score.FORTY);
        } else if (pointWinner.getGameScore().equals(Score.ZERO)) {
            pointWinner.setGameScore(Score.FIFTEEN);
        } else if (pointWinner.getGameScore().equals(Score.FIFTEEN)) {
            pointWinner.setGameScore(Score.THIRTY);
        } else if (pointWinner.getGameScore().equals(Score.THIRTY)) {
            pointWinner.setGameScore(Score.FORTY);
        }
    }

    private void updateGameSetScore(Player pointWinner, Player otherPlayer, Game game) {
        if (pointWinner.getGameWins() == 5 && otherPlayer.getGameWins() <= 4
                || pointWinner.getGameWins() == 6 && otherPlayer.getGameWins() ==5) {
            game.setMatchWinner(Optional.of(pointWinner));
            pointWinner.setGameWins(pointWinner.getGameWins() + 1);
            game.setCurrentGame(0);
        }  else {
            if (pointWinner.getGameWins() == 5 && otherPlayer.getGameWins() == 6) {
                game.setTieBreak(true);
            }
            pointWinner.setGameWins(pointWinner.getGameWins() + 1);
            game.setCurrentGame(game.getCurrentGame() + 1);
        }
    }

    @Override
    public void updateGameScoreWithTieBreakRule(Player pointWinner, Player otherPlayer, Game game) {
        if (pointWinner.getTieBreakScore() >=6 && pointWinner.getTieBreakScore() > otherPlayer.getTieBreakScore()) {
            game.setMatchWinner(Optional.of(pointWinner));
            pointWinner.setGameWins(pointWinner.getGameWins() + 1);
            pointWinner.setTieBreakScore(0);
            otherPlayer.setTieBreakScore(0);
        } else {
            pointWinner.setTieBreakScore(pointWinner.getTieBreakScore() + 1);
        }
    }
}
