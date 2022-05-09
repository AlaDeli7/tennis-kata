package com.elcodeur.rules;

import com.elcodeur.Game;
import com.elcodeur.Player;

public interface TennisRulesInt {

    void updateGameScoreWithRegularRule(Player pointWinner, Player otherPlayer, Game game);

    void updateGameScoreWithTieBreakRule(Player pointWinner, Player otherPlayer, Game game);
}
