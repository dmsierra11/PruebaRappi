package example.danielsierraf.pruebarappi.model;

import android.database.sqlite.SQLiteConstraintException;
import android.util.Log;

import com.raizlabs.android.dbflow.annotation.Database;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

import example.danielsierraf.pruebarappi.api.classes.AppDetail;

/**
 * Created by danielsierraf on 6/12/16.
 */
@Database(name = AppDatabase.NAME, version = AppDatabase.VERSION)
public class AppDatabase {
    private static final String TAG = "Database";

    public static final String NAME = "PruebaRappiDB";

    public static final int VERSION = 1;

    public static void insertEntryToDatabase(List<AppDetail> appDetails){

        for (AppDetail appDetail: appDetails){
            try{
                SQLite.insert(Entry.class)
                        .columns(Entry_Table.imName,
                                Entry_Table.imImage,
                                Entry_Table.summary,
                                Entry_Table.imPrice,
                                Entry_Table.imContentType,
                                Entry_Table.rights,
                                Entry_Table.title,
                                Entry_Table.link,
                                Entry_Table.id,
                                Entry_Table.imArtist,
                                Entry_Table.category,
                                Entry_Table.imReleaseDate)
                        .values(appDetail.getImName().getLabel(),
                                appDetail.getImImage().get(2).getLabel(),
                                appDetail.getSummary().getLabel(),
                                appDetail.getImPrice().getLabel(),
                                appDetail.getImContentType().getAttributes().getLabel(),
                                appDetail.getRights().getLabel(),
                                appDetail.getTitle().getLabel(),
                                appDetail.getLink().getAttributes().getHref(),
                                appDetail.getId().getAttributes().getImId(),
                                appDetail.getImArtist().getLabel(),
                                appDetail.getCategory().getAttributes().getLabel(),
                                appDetail.getImReleaseDate().getAttributes().getLabel())
                        .execute();
            } catch (SQLiteConstraintException e) {
                Log.e(TAG, "UNIQUE CONSTRAINT EXCEPTION");
            }
        }
    }

    public static List<AppDetail> getEntryFromDatabase(){
        //TODO: Get Entry from database
        Log.d(TAG, "Getting entry from database");
        List<Entry> entry = SQLite.select().from(Entry.class).queryList();
        return null;
    }

    public static void deleteEntryTable(){
        SQLite.delete(Entry.class).execute();
    }
}
