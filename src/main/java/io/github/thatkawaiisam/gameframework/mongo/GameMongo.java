package io.github.thatkawaiisam.gameframework.mongo;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;
import io.github.thatkawaiisam.gameframework.AbstractGame;
import lombok.Getter;
import lombok.Setter;

import java.util.Collections;

@Getter @Setter
public class GameMongo {

    private MongoClient client;
    private MongoDatabase database;

    public GameMongo(String database, MongoCredential credential, ServerAddress address) {
        this.client = new MongoClient(address, Collections.singletonList(credential));
        this.database = this.client.getDatabase(database);
    }

    public void cleanup() {
        client.close();
    }
}
