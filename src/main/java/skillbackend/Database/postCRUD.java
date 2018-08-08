package skillbackend.Database;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.json.JSONObject;
import skillbackend.Exceptions.InvalidUserException;
import skillbackend.Model.Posts.PostBase;

import static com.mongodb.client.model.Filters.eq;

public class postCRUD {
    private MongoDB mongoDB = MongoDB.getInstance();
    private MongoDatabase db = mongoDB.getDB();
    private MongoCollection<Document> collection;

    public postCRUD(String category){
        collection = db.getCollection(category);
    }

    public void create(PostBase post){
        Document doc = post.createDBObject();
        collection.insertOne(doc);
    }


    public Object read(String uid) throws Exception {
        Document query = collection.find(eq("uid", uid)).first();
        if (query == null) {
            throw new InvalidUserException();
        }
        return query;
    }

    public String readAll() {
        MongoCursor<Document> iterator = collection.find(eq("retired", "false")).iterator();
        JSONObject list = new JSONObject();
        while (iterator.hasNext()) {
            Document doc = iterator.next();
            System.out.println(doc);
            list.put(doc.getString("uid"), new JSONObject(doc));
        }
        return list.toString();
    }

    public void update(String collectionName) {
    }

    public void delete() {
    }
}
