package skillbackend.Database;

import com.mongodb.client.MongoDatabase;

public interface CRUD {
     MongoDB mongoDB = MongoDB.getInstance();
     MongoDatabase db = mongoDB.getDB();

    //create collection
    public void create(Object obj) throws Exception;
    public void read(String collectionName);
    public void update(String collectionName);
    public void delete();
}
