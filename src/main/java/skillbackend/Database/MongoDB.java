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
        MongoClientOptions.Builder options_builder = new MongoClientOptions.Builder(
        );
        // for production options_builder.sslEnabled(true);
        options_builder.socketTimeout(500);
        options_builder.serverSelectionTimeout(100000);
        options_builder.maxWaitTime(100000);
        options_builder.maxConnectionIdleTime(100000);
        MongoClientURI uri = new MongoClientURI(
                "mongodb+srv://"+ username +":"+ password +"@cluster0-0dasm.mongodb.net/", options_builder);

        MongoClient mongoClient = new MongoClient(uri);


        //uri = new MongoClientURI("mongodb+srv://" + username + ":" + password + "@cluster0-0dasm.mongodb.net/");
        //uri = new MongoClientURI("mongodb://benkim1028:Tkflzls1!@ds121999.mlab.com:21999/skilldb?authMode=scram-sha1");
        //mongoClient = new MongoClient(uri);
        db = mongoClient.getDatabase(database);

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
