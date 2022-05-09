package com.elcodeur;

import com.elcodeur.enums.Score;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class Player {
    private String name;
    private Score score;

    public Player(String name) {
        this.name = name;
        this.score = Score.ZERO;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", score=" + score +
                '}';
    }
}
