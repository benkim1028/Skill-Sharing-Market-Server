package skillbackend.Database;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Arrays;

public class MongoDB {
    private static MongoDB single_instance = null;
    private MongoClientURI uri;
    private MongoClient mongoClient;
    private MongoDatabase db;
    private String username = "skill";
    private String password = "Tkflzls1!";
    private String database = "skillDB";

    private MongoDB() {
        MongoClientOptions.Builder options_builder = new MongoClientOptions.Builder();
        // for production options_builder.sslEnabled(true);
        options_builder.socketTimeout(500);
        options_builder.serverSelectionTimeout(100000);
        options_builder.maxWaitTime(100000);
        options_builder.maxConnectionIdleTime(100000);
        MongoClientURI uri = new MongoClientURI(
                "mongodb://"+username+":"+password+"@ds231739.mlab.com:31739/heroku_6l526rzg", options_builder);
        MongoClient mongoClient = new MongoClient(uri);
        //MongoClient mongoClient = new MongoClient("localhost", 27017);
        db = mongoClient.getDatabase(uri.getDatabase());

    }

    public static MongoDB getInstance() {
        if (single_instance == null)
            return new MongoDB();
        return single_instance;
    }

    public MongoDatabase getDB() {
        return db;
    }
}
/*
MongoDB Compass Login
ID: SkillDev
Password: 싸랑한다1! (영어로)
 */
