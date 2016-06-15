package example.danielsierraf.pruebarappi.api.classes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by danielsierraf on 6/11/16.
 */
public class ImName {
    private String label;

    public ImName(String label){
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
