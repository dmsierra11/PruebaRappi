package example.danielsierraf.pruebarappi.api.classes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by danielsierraf on 6/11/16.
 */
public class Attributes______ {
    @SerializedName("im:id")
    @Expose
    private String imId;
    @SerializedName("term")
    @Expose
    private String term;
    @SerializedName("scheme")
    @Expose
    private String scheme;
    @SerializedName("label")
    @Expose
    private String label;

    public Attributes______(String label){
        this.label = label;
    }

    public String getImId() {
        return imId;
    }

    public void setImId(String imId) {
        this.imId = imId;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
