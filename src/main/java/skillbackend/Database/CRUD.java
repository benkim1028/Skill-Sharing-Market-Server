package skillbackend.Database;

public interface CRUD {
    private MongoDB mongoDB = MongoDB.getInstance();
    protected MongoDB db = mongoDB.getDB();

    //create collection
    public void create(Object obj) throws Exception;
    public void read(String collectionName);
    public void update(String collectionName);
    public void delete();
}
