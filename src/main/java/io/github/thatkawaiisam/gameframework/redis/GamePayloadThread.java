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
            data.put("server", RedstoneBukkitAPI.getCurrentServerName());

            //GameState
            GameState currentState = game.getState();
            boolean joinable = currentState.isJoinable();
            boolean spectatable = currentState.isSpectatable();
            data.put("joinable", joinable + "");
            data.put("spectatable", spectatable + "");

            //Timer
            GameTimer currentTimer = game.getTimers().get(currentState.getName());
            String timerType = currentTimer.getType().name();
            long timerStart = currentTimer.getStartTime();
            long timerEnd = currentTimer.getEndTime();
            data.put("timerType", timerType);
            data.put("timerStart", timerStart + "");
            data.put("timerEnd", timerEnd + "");

            gameRedis.getHelper().runCommand(
                    jedis -> {
                        jedis.hmset("GameFramework:" + game.getId(), data);
                        return jedis;
                    }
            );

            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
