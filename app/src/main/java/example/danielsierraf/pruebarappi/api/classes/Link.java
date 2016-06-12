package example.danielsierraf.pruebarappi.api.classes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by danielsierraf on 6/11/16.
 */
public class Link {
    @SerializedName("attributes")
    @Expose
    private Attributes___ attributes;

    public Attributes___ getAttributes() {
        return attributes;
    }

    public void setAttributes(Attributes___ attributes) {
        this.attributes = attributes;
    }
}
