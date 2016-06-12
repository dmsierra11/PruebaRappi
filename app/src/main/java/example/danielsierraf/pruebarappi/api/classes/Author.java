package example.danielsierraf.pruebarappi.api.classes;

import java.util.jar.Attributes;

/**
 * Created by danielsierraf on 6/11/16.
 */
public class Author {
    private Name name;
    private Uri uri;

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }
}
