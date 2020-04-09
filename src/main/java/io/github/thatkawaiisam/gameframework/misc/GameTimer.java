package io.github.thatkawaiisam.gameframework.misc;

import io.github.thatkawaiisam.gameframework.AbstractGame;
import io.github.thatkawaiisam.timers.TimerManager;
import io.github.thatkawaiisam.timers.api.Timer;
import io.github.thatkawaiisam.timers.api.TimerType;
import lombok.Getter;

@Getter
public abstract class GameTimer<T extends AbstractGame> extends Timer {

    private T game;

    public GameTimer(TimerType type, TimerManager timerManager, T game) {
        super(type, timerManager);
        this.game = game;
    }


}


