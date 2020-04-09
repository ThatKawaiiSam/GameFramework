package io.github.thatkawaiisam.gameframework.mongo;

import com.google.gson.JsonElement;

import java.util.HashMap;

public interface IMongoMatchProvider {

    String getServer();
    String getMap();

    HashMap<String, JsonElement> getMeta();
}
