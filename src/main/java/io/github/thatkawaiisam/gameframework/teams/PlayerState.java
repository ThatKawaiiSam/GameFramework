package io.github.thatkawaiisam.gameframework.teams;

import lombok.Getter;

public class PlayerState {

    @Getter private String name;

    public PlayerState(String name) {
        this.name = name;
    }

}
