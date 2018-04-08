package skillbackend.Model.Posts;

import org.bson.Document;

import java.util.Date;

public class SellPost implements PostBase {
    private String category;
    private String subCategory;
    private String title;
    private String duration;
    private String description;
    private String uid;
    private String retired;
    private Date createdAt;
    private String price;

    public String getPrice() { return price; }

    public void setPrice(String price) { this.price = price; }


    public Date getCreatedAt() { return createdAt; }

    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }

    public String getCategory() { return category; }

    public void setCategory(String category) { this.category = category; }

    public String getSubCategory() { return subCategory; }

    public void setSubCategory(String subCategory) { this.subCategory = subCategory; }

    public String getUid() { return uid; }

    public void setUid(String uid) { this.uid = uid; }

    public String getRetired() { return retired; }

    public void setRetired(String retired) { this.retired = retired; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getDuration() { return duration; }

    public void setDuration(String duration) { this.duration = duration; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    @Override
    public Document createDBObject() {
        Document doc = new Document();

        doc.append("category", getCategory());
        doc.append("subCategory", getSubCategory());
        doc.append("title", getTitle());
        doc.append("duration", getDuration());
        doc.append("description", getDescription());
        doc.append("uid", getUid());
        doc.append("retired", getRetired());
        doc.append("createdAt", getCreatedAt());
        doc.append("price", getPrice());
        return doc;
    }
}
