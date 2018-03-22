package skillbackend.Database;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import skillbackend.Model.Credentials;
import skillbackend.Model.Hash;
import skillbackend.Model.JWT;

public class tokenCRUD{
    MongoDB mongoDB = MongoDB.getInstance();
    MongoDatabase db = mongoDB.getDB();
    private Hash HASH = new Hash();
    private MongoCollection<Document> collection = db.getCollection("user");

    public void create(Object obj){
        JWT token = (JWT) obj;
        Document doc = new Document("token", token.toString());
        collection.insertOne(doc);
    };
    public void read(Object obj){
        JWT token = (JWT) obj;

    };
    public void update(String collectionName){};
    public void delete(){};
}
