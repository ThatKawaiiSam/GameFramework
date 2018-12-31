package io.github.thatkawaiisam.gameframework.teams;

import lombok.Getter;

import java.util.UUID;

@Getter
public class GamePlayer {

    private final UUID uuid;
    private PlayerState state;
    private GameTeam team;

    public GamePlayer(UUID uuid) {
        this.uuid = uuid;
    }
}
