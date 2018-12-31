package io.github.thatkawaiisam.gameframework.settings.impl;

import io.github.thatkawaiisam.gameframework.settings.GameSetting;

public class TeamSizeGameSetting extends GameSetting<Integer> {

    public TeamSizeGameSetting(int value) {
        super("teamSize", value);
    }
}
