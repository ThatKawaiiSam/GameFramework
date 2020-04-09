package io.github.thatkawaiisam.gameframework;

import co.aikar.commands.BaseCommand;
import lombok.Getter;

@Getter
public class GameCommand<T> extends BaseCommand {

    private T game;

    public GameCommand(T game) {
        this.game = game;
    }
}
