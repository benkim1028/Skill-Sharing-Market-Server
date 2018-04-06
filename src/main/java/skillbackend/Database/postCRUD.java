package skillbackend.Database;

import com.mongodb.BasicDBList;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.util.JSON;
import org.bson.Document;
import org.json.JSONObject;
import skillbackend.Exceptions.InvalidUserException;
import skillbackend.Exceptions.UsernameExistException;
import skillbackend.Exceptions.WrongPasswordException;
import skillbackend.Model.Credentials;
import skillbackend.Model.Post;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class postCRUD {
    MongoDB mongoDB = MongoDB.getInstance();
    MongoDatabase db = mongoDB.getDB();
    private MongoCollection<Document> collection = db.getCollection("post");

    public void create(Object obj){
        Post post = (Post) obj;
        Document doc = Post.createDBObject(post);
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
        System.out.println(iterator);

        JSONObject list = new JSONObject();
        while (iterator.hasNext()) {
            Document doc = iterator.next();
            System.out.println(doc);
            list.put(doc.getString("uid"), doc.toJson());
        }
        return list.toString();
    }

    public void update(String collectionName) {
    }

    public void delete() {
    }
}
