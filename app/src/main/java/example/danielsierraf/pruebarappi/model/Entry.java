package example.danielsierraf.pruebarappi.model;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by danielsierraf on 6/12/16.
 */
@Table(database = AppDatabase.class)
public class Entry extends BaseModel {
    @PrimaryKey
    private long id;
    @Column
    private String imName;
    //TODO: Relacion uno a muchos
    @Column
    private String imImage;
    @Column
    private String summary;
    @Column
    private String imPrice;
    @Column
    private String imContentType;
    @Column
    private String rights;
    @Column
    private String title;
    @Column
    private String link;
    @Column
    private String imArtist;
    @Column
    private String category;
    @Column
    private String imReleaseDate;

    public String getImName() {
        return imName;
    }

    public void setImName(String imName) {
        this.imName = imName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImImage() {
        return imImage;
    }

    public void setImImage(String imImage) {
        this.imImage = imImage;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getImPrice() {
        return imPrice;
    }

    public void setImPrice(String imPrice) {
        this.imPrice = imPrice;
    }

    public String getImContentType() {
        return imContentType;
    }

    public void setImContentType(String imContentType) {
        this.imContentType = imContentType;
    }

    public String getRights() {
        return rights;
    }

    public void setRights(String rights) {
        this.rights = rights;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImArtist() {
        return imArtist;
    }

    public void setImArtist(String imArtist) {
        this.imArtist = imArtist;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImReleaseDate() {
        return imReleaseDate;
    }

    public void setImReleaseDate(String imReleaseDate) {
        this.imReleaseDate = imReleaseDate;
    }
}
