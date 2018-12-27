package io.github.thatkawaiisam.gameframework.settings.impl;

import io.github.thatkawaiisam.gameframework.settings.GameSetting;

public class MaxPlayersGameSetting extends GameSetting<Integer> {

    public MaxPlayersGameSetting(int maxPlayers) {
        super("maxPlayers", maxPlayers);
    }
}
