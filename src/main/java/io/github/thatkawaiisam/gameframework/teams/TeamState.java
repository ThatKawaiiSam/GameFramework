package io.github.thatkawaiisam.gameframework.teams;

import lombok.Getter;

public class TeamState {

    @Getter private String name;

    public TeamState(String name) {
        this.name = name;
    }

}
