package io.github.thatkawaiisam.gameframework.settings.impl;

import io.github.thatkawaiisam.gameframework.settings.GameSetting;


public class MinPlayersGameSetting extends GameSetting<Integer> {

    public MinPlayersGameSetting(int minPlayers) {
        super("minPlayers", minPlayers);
    }
}
