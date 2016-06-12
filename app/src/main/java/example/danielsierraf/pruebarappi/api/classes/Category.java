package example.danielsierraf.pruebarappi.api.classes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by danielsierraf on 6/11/16.
 */
public class Category {
    @SerializedName("attributes")
    @Expose
    private Attributes______ attributes;

    public Attributes______ getAttributes() {
        return attributes;
    }

    public void setAttributes(Attributes______ attributes) {
        this.attributes = attributes;
    }
}
