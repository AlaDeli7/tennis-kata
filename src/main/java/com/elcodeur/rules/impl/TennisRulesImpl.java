package com.elcodeur.rules.impl;

import com.elcodeur.ds.Game;
import com.elcodeur.ds.Player;
import com.elcodeur.enums.Score;
import com.elcodeur.rules.TennisRulesInt;

import java.util.Optional;

public class TennisRulesImpl implements TennisRulesInt {
    @Override
    public void updateGameScoreWithRegularRule(Player pointWinner, Player otherPlayer, Game game) {
        if (Score.ADV.equals(pointWinner.getGameScore())
                || (Score.FORTY.equals(pointWinner.getGameScore())
                && !Score.FORTY.equals(otherPlayer.getGameScore())
                && !Score.ADV.equals(otherPlayer.getGameScore()))) {
            game.setGameWinner(Optional.of(pointWinner));
            pointWinner.setGameScore(Score.ZERO);
            otherPlayer.setGameScore(Score.ZERO);
            updateGameSetScore(pointWinner, otherPlayer, game);
        } else if (Score.FORTY.equals(pointWinner.getGameScore())
                && Score.ADV.equals(otherPlayer.getGameScore())) {
            pointWinner.setGameScore(Score.DEUCE);
            otherPlayer.setGameScore(Score.DEUCE);
        } else if (Score.DEUCE.equals(pointWinner.getGameScore())
                ||
                (Score.FORTY.equals(pointWinner.getGameScore())
                        && Score.FORTY.equals(otherPlayer.getGameScore()))) {
            pointWinner.setGameScore(Score.ADV);
            otherPlayer.setGameScore(Score.FORTY);
        } else if (Score.ZERO.equals(pointWinner.getGameScore())) {
            pointWinner.setGameScore(Score.FIFTEEN);
        } else if (Score.FIFTEEN.equals(pointWinner.getGameScore())) {
            pointWinner.setGameScore(Score.THIRTY);
        } else if (Score.THIRTY.equals(pointWinner.getGameScore())) {
            pointWinner.setGameScore(Score.FORTY);
        }
    }

    @Override
    public void updateGameScoreWithTieBreakRule(Player pointWinner, Player otherPlayer, Game game) {
        if (pointWinner.getTieBreakScore() >=6 && pointWinner.getTieBreakScore() > otherPlayer.getTieBreakScore()) {
            game.setMatchWinner(Optional.of(pointWinner));
            pointWinner.incrementGameWins();
            pointWinner.setTieBreakScore(0);
            otherPlayer.setTieBreakScore(0);
        } else {
            pointWinner.incrementTieBreakScore();
        }
    }

    private void updateGameSetScore(Player pointWinner, Player otherPlayer, Game game) {
        if (pointWinner.getGameWins() == 5 && otherPlayer.getGameWins() <= 4
                || pointWinner.getGameWins() == 6 && otherPlayer.getGameWins() ==5) {
            game.setMatchWinner(Optional.of(pointWinner));
            pointWinner.incrementGameWins();
            game.setCurrentGame(0);
        }  else {
            if (pointWinner.getGameWins() == 5 && otherPlayer.getGameWins() == 6) {
                game.setTieBreak(true);
            }
            pointWinner.incrementGameWins();
            game.incrementCurrentGame();
        }
    }
}
