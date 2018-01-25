package skillbackend.Database;

import com.mongodb.client.MongoCollection;
import org.bson.Document;
import skillbackend.Model.Credentials;
import skillbackend.Model.Hash;
import skillbackend.Model.JWT;

public class tokenCRUD implements CRUD{
    private static final Hash HASH = new Hash();
    private MongoCollection<Document> collection = db.getCollection("user");

    public void create(Object obj) throws Exception{
        if (!(obj instanceof JWT))
            throw new Exception();
        Credentials credentials = (Credentials) obj;
        Document doc = new Document("username", credentials.getUsername())
                .append("password", HASH.hashPassword(credentials.getPassword()));
        collection.insertOne(doc);
    };
    public void read(String collectionName){};
    public void update(String collectionName){};
    public void delete(){};
}
