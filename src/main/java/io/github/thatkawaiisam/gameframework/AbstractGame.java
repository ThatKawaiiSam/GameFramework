package io.github.thatkawaiisam.gameframework;

import io.github.thatkawaiisam.gameframework.settings.GameSetting;
import io.github.thatkawaiisam.gameframework.settings.impl.MaxPlayersGameSetting;
import io.github.thatkawaiisam.gameframework.settings.impl.MinPlayersGameSetting;
import io.github.thatkawaiisam.timers.AbstractTimer;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Getter @Setter
public abstract class AbstractGame {

    private String id;
    private List<GameSetting> gameSettings = new ArrayList<>();
    private GameState state;
    private Map<String, AbstractTimer> timers = new ConcurrentHashMap<>();

    public AbstractGame(String id) {
        this.id = id;
        gameSettings.add(new MaxPlayersGameSetting(1));
        gameSettings.add(new MinPlayersGameSetting(1));
    }

    public GameSetting getGameSetting(String settingID) {
        for (GameSetting setting : gameSettings) {
            if (setting.getId().equalsIgnoreCase(settingID)) {
                return setting;
            }
        }
        return null;
    }

    public abstract void start();

    public abstract void cleanup();

    public abstract void preJoin(UUID uuid);

    public abstract void join(Player player);

    public abstract void leave(Player player);

    public abstract void handleDeath(Player player);

}
