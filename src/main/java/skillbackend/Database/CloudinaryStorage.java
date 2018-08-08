package skillbackend.Database;

import com.cloudinary.Cloudinary;

import java.util.HashMap;
import java.util.Map;

public class CloudinaryStorage {
    private static Cloudinary cloudinary = initializeStorage();

    private static Cloudinary initializeStorage(){
        Map config = new HashMap();
        config.put("cloud_name", "hcvi5x32d");
        config.put("api_key", "653286744731362");
        config.put("api_secret", "utOisNmKMdGJVPTwPDwByJIGLfk");
        return new Cloudinary(config);
    }

    public static Cloudinary getCloudinaryInstance(){
        return cloudinary;
    }
}
