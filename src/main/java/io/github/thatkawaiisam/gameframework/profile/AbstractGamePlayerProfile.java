package io.github.thatkawaiisam.gameframework.profile;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

@Getter @Setter
public abstract class AbstractGamePlayerProfile {

    private UUID uuid;
    private String name;
    private AbstractStatsPlayerProfile stats;

    public AbstractGamePlayerProfile(Player player) {
        this.uuid = player.getUniqueId();
        this.name = player.getName();
    }

    public abstract void load();
    public abstract void save();

    public Player getPlayer() {
        Player player = Bukkit.getPlayer(uuid);

        if (player != null) {
            name = player.getName();
        }

        return player;
    }
}
