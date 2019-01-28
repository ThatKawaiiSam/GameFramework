package io.github.thatkawaiisam.gameframework.redis;

import io.github.thatkawaiisam.gameframework.AbstractGame;
import io.github.thatkawaiisam.redstone.shared.redis.RedisHelper;
import lombok.Getter;

@Getter
public class GameRedis {

    private AbstractGame game;

    private GamePayloadThread payloadThread;
    private RedisHelper helper;

    public GameRedis(AbstractGame game) {
        this.game = game;
        this.payloadThread = new GamePayloadThread(this);
    }
}
