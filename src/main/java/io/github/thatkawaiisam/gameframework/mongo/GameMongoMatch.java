package io.github.thatkawaiisam.gameframework.mongo;

import com.google.gson.JsonObject;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.ReplaceOptions;
import io.github.thatkawaiisam.gameframework.AbstractGame;
import org.apache.commons.lang3.RandomStringUtils;
import org.bson.Document;


public class GameMongoMatch {

    private GameMongo mongo;
    private AbstractGame game;
    private IMongoMatchProvider matchProvider;

    public GameMongoMatch(AbstractGame game, GameMongo mongo, IMongoMatchProvider matchProvider) {
        this.game = game;
        this.mongo = mongo;
        this.matchProvider = matchProvider;
    }

    public void save() {
        String matchID = generateMatchString();
        game.messageEveryone("&e&oAttempting to load match into database");
        JsonObject matchData = new JsonObject();
        // ID
        matchData.addProperty("id", matchID);

        // Provider Defaults
        if (matchProvider.getServer() != null) {
            matchData.addProperty("server", matchProvider.getServer());
        }
        if (matchProvider.getMap() != null) {
            matchData.addProperty("map", matchProvider.getMap());
        }

        // Add extra meta
        if (matchProvider.getMeta() != null) {
            for (String meta : matchProvider.getMeta().keySet()) {
                matchData.add(meta, matchProvider.getMeta().get(meta));
            }
        }

        //Upload to mongo database
        mongo.getDatabase()
                .getCollection("matches")
                .replaceOne(
                        Filters.eq("id", matchID),
                        Document.parse(matchData.toString()),
                        new ReplaceOptions().upsert(true)
                );

        game.messageEveryone("&a&oSuccessefully loaded match to database.");
        game.messageEveryone("&a&oYou can now view the game at https://orionmc.net/matches/" + matchID);
    }

    private String generateMatchString() {
        while(true) {
            String id = RandomStringUtils.randomAlphanumeric(8);
            Document document = mongo.getDatabase()
                    .getCollection("matches")
                    .find(Filters.eq("id", id))
                    .first();
            if (document == null) {
                return id;
            }
        }
    }
}
