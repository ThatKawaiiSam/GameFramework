package io.github.thatkawaiisam.gameframework.redis;

import java.util.HashMap;

public interface IGameRedisMetadataProvider {

    HashMap<String, String> getMetaData();

}
