package example.danielsierraf.pruebarappi.api.classes;

/**
 * Created by danielsierraf on 6/11/16.
 */
public class Attributes__ {
    private String term;
    private String label;

    public Attributes__(String label){
        this.label = label;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
