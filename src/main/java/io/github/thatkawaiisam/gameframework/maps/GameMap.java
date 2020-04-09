package io.github.thatkawaiisam.gameframework.maps;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.github.thatkawaiisam.utils.LocationUtility;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.HashMap;
import java.util.Map;

@Getter @Setter
public class GameMap {

    private String author = "N/A", link = "N/A", displayName = "N/A";
    private String id;
    private Map<String, Location> locations = new HashMap<>();

    public GameMap(JsonObject jsonObject) {
        load(jsonObject);
    }

    public void load(JsonObject jsonObject) {
        this.id = jsonObject.get("id").getAsString();

        if (jsonObject.has("author")) {
            this.author = jsonObject.get("author").getAsString();
        }

        if (jsonObject.has("link")) {
            this.link = jsonObject.get("link").getAsString();
        }

        if (jsonObject.has("displayName")) {
            this.displayName = jsonObject.get("displayName").getAsString();
        }

        if (jsonObject.has("locations")) {
            JsonArray array = jsonObject.get("locations").getAsJsonArray();
            for (JsonElement element : array) {
                JsonObject innerObject = element.getAsJsonObject();
                locations.put(
                        innerObject.get("id").getAsString(),
                        LocationUtility.parseToLocation(innerObject.get("value").getAsString())
                );
            }
        }
    }

    public void refreshWorld(String worldName) {
        for (String id : locations.keySet()) {
            locations.get(id).setWorld(Bukkit.getWorld(worldName));
        }
    }
}
