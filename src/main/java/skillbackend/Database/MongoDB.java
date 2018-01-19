package skillbackend.Database;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

public class MongoDB {

    private static MongoClientURI uri = new MongoClientURI("mongodb+srv://skill:XRQuvlG1Qe0Oy4cS@cluster0.mongodb.net/");
    private static MongoClient mongoClient = new MongoClient(uri);
    private static MongoDatabase database = mongoClient.getDatabase("test");
//    public static void main (String[] args){
//        System.out.println(database);
//    }
}
