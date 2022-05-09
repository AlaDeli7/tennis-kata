package com.elcodeur.ds;

import com.elcodeur.enums.Score;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class Player {
    private String name;
    private Score gameScore;
    private int tieBreakScore;
    private int gameWins;

    public Player(String name) {
        this.name = name;
        this.gameScore = Score.ZERO;
    }

    public void incrementTieBreakScore() {
        tieBreakScore += 1;
    }

    public void incrementGameWins() {
        gameWins += 1;
    }
}
