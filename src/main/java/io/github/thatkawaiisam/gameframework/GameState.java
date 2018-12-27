package io.github.thatkawaiisam.gameframework;

import lombok.Getter;

public class GameState {

    @Getter private String name;

    public GameState(String name) {
        this.name = name;
    }

}
