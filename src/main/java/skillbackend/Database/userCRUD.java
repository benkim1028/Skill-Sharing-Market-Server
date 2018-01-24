package skillbackend.Database;

import skillbackend.Model.Credentials;
import skillbackend.Model.Hash;

public class userCRUD implements CRUD {
    private static final hash HASH = new Hash();
    private MongoCollection<Document> collection = db.getCollection("user");

    @Override
    public void create(Object obj) throws Exception {
        if(obj instanceof Credentials){
            continue;
        } else {
            throw new Exception();
        }
        Document doc = new Document("username", credentials.getUsername())
                            .append("password", HASH.hashPassword(credentials.getPassword()));
        collection.insertOne(doc);
    }
}
