package example.danielsierraf.pruebarappi.model;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by danielsierraf on 6/12/16.
 */
@Database(name = AppDatabase.NAME, version = AppDatabase.VERSION)
public class AppDatabase {
    public static final String NAME = "PruebaRappiDB";

    public static final int VERSION = 1;
}
