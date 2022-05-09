package com.elcodeur.rules;

import com.elcodeur.ds.Game;
import com.elcodeur.ds.Player;

public interface TennisRulesInt {

    void updateGameScoreWithRegularRule(Player pointWinner, Player otherPlayer, Game game);

    void updateGameScoreWithTieBreakRule(Player pointWinner, Player otherPlayer, Game game);
}
