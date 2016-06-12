package example.danielsierraf.pruebarappi.api.classes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by danielsierraf on 6/11/16.
 */
public class Feed {
    @SerializedName("author")
    @Expose
    private Author author;
    @SerializedName("entry")
    @Expose
    private List<AppDetail> entry = new ArrayList<AppDetail>();
    @SerializedName("updated")
    @Expose
    private Updated updated;
    @SerializedName("rights")
    @Expose
    private Rights rights;
    @SerializedName("title")
    @Expose
    private Title title;
    @SerializedName("icon")
    @Expose
    private Icon icon;
    @SerializedName("link")
    @Expose
    private List<Link> link = new ArrayList<Link>();
    @SerializedName("id")
    @Expose
    private Id_ id;

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public List<AppDetail> getEntry() {
        return entry;
    }

    public void setEntry(List<AppDetail> entry) {
        this.entry = entry;
    }

    public Updated getUpdated() {
        return updated;
    }

    public void setUpdated(Updated updated) {
        this.updated = updated;
    }

    public Rights getRights() {
        return rights;
    }

    public void setRights(Rights rights) {
        this.rights = rights;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public Icon getIcon() {
        return icon;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    public List<Link> getLink() {
        return link;
    }

    public void setLink(List<Link> link) {
        this.link = link;
    }

    public Id_ getId() {
        return id;
    }

    public void setId(Id_ id) {
        this.id = id;
    }
}
