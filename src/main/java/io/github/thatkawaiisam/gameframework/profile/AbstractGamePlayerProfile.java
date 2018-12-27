package io.github.thatkawaiisam.gameframework.profile;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

@Getter @Setter
public abstract class AbstractGamePlayerProfile {

    private UUID uuid;
    private AbstractStatsPlayerProfile stats;

    public AbstractGamePlayerProfile(Player player) {
        this.uuid = player.getUniqueId();
    }

    public abstract void load();
    public abstract void save();

    public Player getPlayer() {
        return Bukkit.getPlayer(uuid);
    }
}
