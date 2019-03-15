package io.github.thatkawaiisam.gameframework.mongo;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import io.github.thatkawaiisam.gameframework.AbstractGame;
import lombok.Getter;
import lombok.Setter;
import org.bson.Document;

@Getter @Setter
public class GameMongo {

    private AbstractGame game;

    private MongoClient client;
    private MongoDatabase database;

    private GameMongo(AbstractGame game) {
        this.game = game;
    }
}
