package skillbackend.Database;

import com.mongodb.client.MongoDatabase;

public interface CRUD {
     MongoDB mongoDB = MongoDB.getInstance();
     MongoDatabase db = mongoDB.getDB();

    //create collection
    public void create(Object obj);
    public void read(Object obj) throws Exception;
    public void update(String collectionName);
    public void delete();
}
