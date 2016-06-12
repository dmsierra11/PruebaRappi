package example.danielsierraf.pruebarappi.api.classes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by danielsierraf on 6/11/16.
 */
public class ImContentType {
    @SerializedName("attributes")
    @Expose
    private Attributes__ attributes;

    public Attributes__ getAttributes() {
        return attributes;
    }

    public void setAttributes(Attributes__ attributes) {
        this.attributes = attributes;
    }
}
