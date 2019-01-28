package io.github.thatkawaiisam.gameframework;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GameState {

    private String name;
    private boolean joinable = false, spectatable = false;

    public GameState(String name) {
        this.name = name;
    }

}
