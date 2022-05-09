package com.elcodeur;

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
}
