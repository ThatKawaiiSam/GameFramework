package io.github.thatkawaiisam.gameframework.teams;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

@Getter @Setter
public class GamePlayer {

    private final UUID uuid;
    private PlayerState state;
    private GameTeam team;

    public GamePlayer(UUID uuid) {
        this.uuid = uuid;
    }

    public Player toBukkitPlayer() {
        return Bukkit.getPlayer(uuid);
    }
}
