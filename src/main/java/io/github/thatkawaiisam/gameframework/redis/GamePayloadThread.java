package io.github.thatkawaiisam.gameframework.redis;

import io.github.thatkawaiisam.gameframework.AbstractGame;
import io.github.thatkawaiisam.gameframework.GameState;
import io.github.thatkawaiisam.gameframework.misc.GameTimer;
import io.github.thatkawaiisam.redstone.bukkit.RedstoneBukkitAPI;

import java.util.HashMap;
import java.util.Map;

public class GamePayloadThread extends Thread {

    private GameRedis gameRedis;

    public GamePayloadThread(GameRedis gameRedis) {
        setName("GameFramework-Payload");
        this.gameRedis = gameRedis;
        this.start();
    }

    @Override
    public void run() {
        while(true) {
            //Data
            Map<String, String> data = new HashMap<>();

            //Game
            final AbstractGame game = gameRedis.getGame();
            data.put("id", game.getId());

            //GameState
            GameState currentState = game.getState();
            if (currentState != null) {
                boolean joinable = currentState.isJoinable();
                boolean spectatable = currentState.isSpectatable();
                data.put("currentState", currentState.getName());
                data.put("joinable", joinable + "");
                data.put("spectatable", spectatable + "");
            }

            //Timer
            if (game.getTimers().size() > 0) {
                if (currentState != null) {
                    GameTimer currentTimer = game.getTimers().get(currentState.getName().toLowerCase());

                    if (currentTimer != null) {
                        String timerType = currentTimer.getType().name();
                        long timerStart = currentTimer.getStartTime();
                        long timerEnd = currentTimer.getEndTime();
                        data.put("timerType", timerType);
                        data.put("timerStart", timerStart + "");
                        data.put("timerEnd", timerEnd + "");
                    }
                }
            }

            //Extra Meta
            if (this.gameRedis.getMetadataProvider() != null) {
                Map<String, String> metaMap = this.gameRedis.getMetadataProvider().getMetaData();
                if (metaMap != null) {
                    for (String key : metaMap.keySet()) {
                        data.put("meta_" + key, metaMap.get(key));
                    }
                }
            }

            gameRedis.getHelper().runCommand(
                    jedis -> {
                        jedis.hmset("GameFramework:" + game.getId(), data);
                        return jedis;
                    }
            );

            try {
                sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
