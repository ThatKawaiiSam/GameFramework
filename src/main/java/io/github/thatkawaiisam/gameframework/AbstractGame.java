package io.github.thatkawaiisam.gameframework;

import io.github.thatkawaiisam.gameframework.misc.GameTimer;
import io.github.thatkawaiisam.gameframework.redis.GameRedis;
import io.github.thatkawaiisam.gameframework.settings.GameSetting;
import io.github.thatkawaiisam.gameframework.settings.impl.MaxPlayersGameSetting;
import io.github.thatkawaiisam.gameframework.settings.impl.MinPlayersGameSetting;
import io.github.thatkawaiisam.gameframework.settings.impl.TeamSizeGameSetting;
import io.github.thatkawaiisam.gameframework.teams.GamePlayer;
import io.github.thatkawaiisam.gameframework.teams.GameTeam;
import io.github.thatkawaiisam.utils.MessageUtility;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Getter @Setter
public abstract class AbstractGame {

    private String id;
    private List<GameSetting> gameSettings = new ArrayList<>();
    private GameState state;
    private GameRedis redis;
    private Map<String, GameTimer> timers = new ConcurrentHashMap<>();
    private List<GameTeam> teams = new ArrayList<>();
    private List<GamePlayer> players = new ArrayList<>();

    public AbstractGame(String id) {
        this.id = id;
        gameSettings.add(new MaxPlayersGameSetting(1));
        gameSettings.add(new MinPlayersGameSetting(1));
        gameSettings.add(new TeamSizeGameSetting(1));
    }

    public void setMaxPlayers(int maxPlayers) {
        GameSetting<Integer> maxPlayersSetting = getGameSetting("maxPlayers");
        maxPlayersSetting.setValue(maxPlayers);
    }

    public void setMinPlayers(int minPlayers) {
        GameSetting<Integer> minPlayersSetting = getGameSetting("minPlayers");
        minPlayersSetting.setValue(minPlayers);
    }

    public void setTeamSize(int teamSize) {
        GameSetting<Integer> gameSetting = getGameSetting("teamSize");
        gameSetting.setValue(1);
    }

    public List<Player> getPlayersFromGamePlayers(List<GamePlayer> gamePlayers) {
        List<Player> players = new ArrayList<>();
        for (GamePlayer gamePlayer : gamePlayers) {
            players.add(Bukkit.getPlayer(gamePlayer.getUuid()));
        }
        return players;
    }

    public List<UUID> getUUIDSFromGamePlayers(List<GamePlayer> gamePlayers) {
        List<UUID> uuids = new ArrayList<>();
        for (GamePlayer gamePlayer : gamePlayers) {
            uuids.add(gamePlayer.getUuid());
        }
        return uuids;
    }

    public <T> T getGameSetting(String settingID) {
        for (GameSetting<?> setting : gameSettings) {
            if (setting.getId().equalsIgnoreCase(settingID)) {
                return (T) GameSetting.class.cast(setting);
            }
        }
        return null;
    }

    public void messageEveryone(String message) {
        for (GamePlayer gamePlayer : getPlayers()) {
            gamePlayer.toBukkitPlayer().sendMessage(MessageUtility.formatMessage(message));
        }
    }

    public abstract void start();

    public abstract void cleanup();

    public abstract void preJoin(UUID uuid);

    public abstract void join(Player player);

    public abstract void leave(Player player);

    public abstract void handleDeath(Player player);

}
