package example.danielsierraf.pruebarappi.api.classes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by danielsierraf on 6/11/16.
 */
public class ImArtist {
    @SerializedName("label")
    @Expose
    private String label;
    @SerializedName("attributes")
    @Expose
    private Attributes_____ attributes;

    public ImArtist(String label){
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Attributes_____ getAttributes() {
        return attributes;
    }

    public void setAttributes(Attributes_____ attributes) {
        this.attributes = attributes;
    }
}
