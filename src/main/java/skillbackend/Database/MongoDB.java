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

    private MongoDB(){
        uri = new MongoClientURI(
                "mongodb+srv://skill:x4R2cHYkhLp16n8U@cluster0-0dasm.mongodb.net/");
        mongoClient = new MongoClient(uri);
    }

    public static MongoDB getInstance(){
        if(single_instance == null)
            return new MongoDB();
        return single_instance;
    }

    static public void main(String args[]){
        MongoClientURI uri = new MongoClientURI(
                "mongodb+srv://skill:x4R2cHYkhLp16n8U@cluster0-0dasm.mongodb.net/");

        MongoClient mongoClient = new MongoClient(uri);
        MongoDatabase db = mongoClient.getDatabase("skillDB");
        System.out.println("Successfully connected");
        //db.createCollection("user");
        System.out.println("created");
        MongoCollection<Document> collection = db.getCollection("user");
        Document doc = new Document("username", "MongoDB")
                .append("type", "database")
                .append("count", 1)
                .append("info", new Document("x", 203).append("y", 102));
        collection.insertOne(doc);
        System.out.println("added");

    }

}
/*
MongoDB Compass Login
ID: SkillDev
Password: 싸랑한다1! (영어로)
 */
