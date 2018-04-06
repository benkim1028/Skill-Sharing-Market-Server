package skillbackend.Model;

import org.bson.Document;

public class Post {
    private String skill;
    private String title;
    private String duration;
    private String description;
    private String uid;
    private String retired;

    public String getUid() { return uid; }

    public void setUid(String uid) { this.uid = uid; }

    public String getRetired() { return retired; }

    public void setRetired(String retired) { this.retired = retired; }

    public String getSkill() { return skill; }

    public void setSkill(String skill) { this.skill = skill; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getDuration() { return duration; }

    public void setDuration(String duration) { this.duration = duration; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }


    public static Document createDBObject(Post post) {
        Document doc = new Document();

        doc.append("skill", post.getSkill());
        doc.append("title", post.getTitle());
        doc.append("duration", post.getDuration());
        doc.append("description", post.getDescription());
        doc.append("uid", post.getUid());
        doc.append("retired", post.getRetired());
        return doc;
    }
}
