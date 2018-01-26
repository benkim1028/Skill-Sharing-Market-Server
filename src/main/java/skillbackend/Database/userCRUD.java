package skillbackend.Database;

import com.mongodb.client.MongoCollection;
import org.bson.Document;
import skillbackend.Model.Credentials;
import skillbackend.Model.Hash;
import static com.mongodb.client.model.Filters.*;

public class userCRUD implements CRUD {
    private Hash HASH = new Hash();
    private MongoCollection<Document> collection = db.getCollection("user");

    @Override
    public void create(Object obj) {
        Credentials credentials = (Credentials) obj;
        Document doc = new Document("username", credentials.getUsername())
                .append("password", HASH.hashPassword(credentials.getPassword()));
        collection.insertOne(doc);
    }
    @Override
    public void read(Object obj) throws Exception{
        Credentials credentials = (Credentials) obj;
        Document query = collection.find(eq("username", credentials.getUsername())).first();
        if(query == null){
            throw new Exception();
        } else if(!HASH.checkPassword(credentials.getPassword(), query.getString("password"))){
            throw new Exception();
        }
    }

    @Override
    public void update(String collectionName){}

    @Override
    public void delete(){}

}
