package io.github.thatkawaiisam.gameframework.misc;

import io.github.thatkawaiisam.gameframework.AbstractGame;
import io.github.thatkawaiisam.timers.api.Timer;
import io.github.thatkawaiisam.timers.api.TimerType;
import lombok.Getter;

@Getter
public class GameTimer extends Timer {

    private AbstractGame game;

    public GameTimer(TimerType type, AbstractGame game) {
        super(type);
        this.game = game;
    }

    @Override
    public void tick() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onComplete() {

    }
}
