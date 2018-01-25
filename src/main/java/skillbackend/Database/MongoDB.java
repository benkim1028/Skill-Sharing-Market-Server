package skillbackend.Database;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MongoDB {
    private static MongoDB single_instance = null;
    private MongoClientURI uri;
    private MongoClient mongoClient;
    private MongoDatabase db;
    private String username = "skill";
    private String password = "x4R2cHYkhLp16n8U";

    private MongoDB(){
        uri = new MongoClientURI("mongodb+srv://" + username + ":" + password + "@cluster0-0dasm.mongodb.net/");
        mongoClient = new MongoClient(uri);
        db = mongoClient.getDatabase("skillDB");
    }

    public static MongoDB getInstance(){
        if(single_instance == null)
            return new MongoDB();
        return single_instance;
    }
    
    public MongoDatabase getDB(){
        return db;
    }
}
/*
MongoDB Compass Login
ID: SkillDev
Password: 싸랑한다1! (영어로)
 */
