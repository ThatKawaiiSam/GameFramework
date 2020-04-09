package io.github.thatkawaiisam.gameframework.redis;

import io.github.thatkawaiisam.gameframework.AbstractGame;
import io.github.thatkawaiisam.jedis.helper.JedisCredentials;
import io.github.thatkawaiisam.redstone.shared.redis.RedisHelper;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GameRedis {

    private AbstractGame game;

    private GamePayloadThread payloadThread;
    private RedisHelper helper = new RedisHelper(new JedisCredentials("127.0.0.1", "", 6379));
    private IGameRedisMetadataProvider metadataProvider;

    public GameRedis(AbstractGame game) {
        this.game = game;
        this.payloadThread = new GamePayloadThread(this);
    }

    public GameRedis(AbstractGame game, IGameRedisMetadataProvider metadataProvider) {
        this(game);
        this.metadataProvider = metadataProvider;
    }

    public void cleanup() {
        this.payloadThread.stop();
        helper.runCommand(redis -> {
            redis.del("GameFramework:" + game.getId());
            return redis;
        });
    }
}
