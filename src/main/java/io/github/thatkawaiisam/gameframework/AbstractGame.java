package io.github.thatkawaiisam.gameframework;

import io.github.thatkawaiisam.gameframework.misc.GameTimer;
import io.github.thatkawaiisam.gameframework.mongo.GameMongo;
import io.github.thatkawaiisam.gameframework.mongo.GameMongoMatch;
import io.github.thatkawaiisam.gameframework.redis.GameRedis;
import io.github.thatkawaiisam.gameframework.settings.GameSetting;
import io.github.thatkawaiisam.gameframework.settings.impl.MaxPlayersGameSetting;
import io.github.thatkawaiisam.gameframework.settings.impl.MinPlayersGameSetting;
import io.github.thatkawaiisam.gameframework.settings.impl.TeamSizeGameSetting;
import io.github.thatkawaiisam.gameframework.teams.GamePlayer;
import io.github.thatkawaiisam.gameframework.teams.GameTeam;
import io.github.thatkawaiisam.gameframework.teams.PlayerState;
import io.github.thatkawaiisam.utils.MessageUtility;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@Getter @Setter
public abstract class AbstractGame {

    private String id;
    private GameState state;
    private GameRedis redis;
    private GameMongo mongo;
    private GameMongoMatch mongoMatch;
    private Map<String, GameTimer<? extends AbstractGame>> timers = new ConcurrentHashMap<String, GameTimer<? extends AbstractGame>>();
    private List<GameSetting<?>> gameSettings = new CopyOnWriteArrayList<>();
    private List<GameTeam> teams = new CopyOnWriteArrayList<>();
    private List<GamePlayer> players = new CopyOnWriteArrayList<>();
    private List<GameModule> modules = new CopyOnWriteArrayList<>();
    private List<GameListener> gameListeners = new CopyOnWriteArrayList<>();

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
        GameSetting<Integer> gameSetting = this.getGameSetting("teamSize");
        gameSetting.setValue(teamSize);
    }

    public GamePlayer getGamePlayer(UUID uuid) {
        for (GamePlayer gamePlayer : getPlayers()) {
            if (gamePlayer.getUuid().equals(uuid)) {
                return gamePlayer;
            }
        }
        return null;
    }

    public GameTeam getGameTeam(String id) {
        for (GameTeam team : getTeams()) {
            if (team.getId().equalsIgnoreCase(id)) {
                return team;
            }
        }
        return null;
    }

    public List<GamePlayer> getGamePlayersFromState(PlayerState playerState) {
        List<GamePlayer> list = new ArrayList<>();
        for (GamePlayer gamePlayer : getPlayers()) {
            if (gamePlayer.getState() == playerState) {
                list.add(gamePlayer);
            }
        }
        return list;
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

    public <T extends GameSetting> T getGameSetting(String settingID) {
        for (GameSetting<?> setting : gameSettings) {
            if (setting.getId().equalsIgnoreCase(settingID)) {
                return (T) setting;
            }
        }
        return null;
    }

    public <T extends GameModule> T getGameModule(String id) {
        for (GameModule<?> module : getModules()) {
            if (module.getModuleName().equalsIgnoreCase(id)) {
                return (T) module;
            }
        }
        return null;
    }

    public void messageEveryone(String message) {
        for (GamePlayer gamePlayer : getPlayers()) {
            if (gamePlayer.toBukkitPlayer() == null || !gamePlayer.toBukkitPlayer().isOnline()) {
                continue;
            }
            gamePlayer.toBukkitPlayer().sendMessage(MessageUtility.formatMessage(message));
        }
    }

    public abstract void start();

    public abstract void cleanup();

    public abstract void join(Player player);

    public abstract void leave(Player player);

    public abstract boolean isPlayerApplicable(Player player);

}
