package skillbackend.Database;

import com.mongodb.client.MongoCollection;
import org.bson.Document;
import skillbackend.Exceptions.InvalidUserException;
import skillbackend.Exceptions.WrongPasswordException;
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
                .append("password", HASH.hashPassword(credentials.getPassword()))
                .append("name", credentials.getName());
        collection.insertOne(doc);
    }
    @Override
    public void read(Object obj) throws Exception{
        Credentials credentials = (Credentials) obj;
        Document query = collection.find(eq("username", credentials.getUsername())).first();
        if(query == null){
            throw new InvalidUserException();
        } else if(!HASH.checkPassword(credentials.getPassword().trim(), query.getString("password"))){
            throw new WrongPasswordException();
        }

    }

    @Override
    public void update(String collectionName){}

    @Override
    public void delete(){}

    public static void main (String[] args){
        MongoCollection<Document> collection = db.getCollection("user");
        String test = Hash.hashPassword("test");
        Document query = collection.find(eq("username", "ssanga114")).first();
        System.out.println(test);
        System.out.println(Hash.checkPassword("Tkflzls1!", query.getString("password")));

    }


}
