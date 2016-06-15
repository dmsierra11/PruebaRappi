package example.danielsierraf.pruebarappi.api.classes;

/**
 * Created by danielsierraf on 6/11/16.
 */
public class Attributes___ {
    private String rel;
    private String type;
    private String href;

    public Attributes___(){

    }

    public Attributes___(String href){
        this.href = href;
    }

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}
