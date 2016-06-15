package example.danielsierraf.pruebarappi.model;

import android.database.sqlite.SQLiteConstraintException;
import android.util.Log;

import com.raizlabs.android.dbflow.annotation.Database;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.List;

import example.danielsierraf.pruebarappi.api.classes.AppDetail;
import example.danielsierraf.pruebarappi.api.classes.Attributes__;
import example.danielsierraf.pruebarappi.api.classes.Attributes___;
import example.danielsierraf.pruebarappi.api.classes.Attributes____;
import example.danielsierraf.pruebarappi.api.classes.Attributes______;
import example.danielsierraf.pruebarappi.api.classes.Attributes_______;
import example.danielsierraf.pruebarappi.api.classes.Category;
import example.danielsierraf.pruebarappi.api.classes.Id;
import example.danielsierraf.pruebarappi.api.classes.ImArtist;
import example.danielsierraf.pruebarappi.api.classes.ImContentType;
import example.danielsierraf.pruebarappi.api.classes.ImImage;
import example.danielsierraf.pruebarappi.api.classes.ImName;
import example.danielsierraf.pruebarappi.api.classes.ImPrice;
import example.danielsierraf.pruebarappi.api.classes.ImReleaseDate;
import example.danielsierraf.pruebarappi.api.classes.Link;
import example.danielsierraf.pruebarappi.api.classes.Rights;
import example.danielsierraf.pruebarappi.api.classes.Summary;
import example.danielsierraf.pruebarappi.api.classes.Title;

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
        List<Entry> entries = SQLite.select().from(Entry.class).queryList();
        List<AppDetail> apps = new ArrayList<>();
        for (Entry entry: entries){
            AppDetail appDetail = new AppDetail();

            appDetail.setImName(new ImName(entry.getImName()));

            final ImImage image = new ImImage(entry.getImImage());
            List<ImImage> images = new ArrayList<ImImage>(){
                {
                    add(image);
                    add(image);
                    add(image);
                }
            };
            appDetail.setImImage(images);

            appDetail.setSummary(new Summary(entry.getSummary()));
            appDetail.setImPrice(new ImPrice(entry.getImPrice()));
            appDetail.setImContentType(new ImContentType(new Attributes__(entry.getImContentType())));
            appDetail.setRights(new Rights(entry.getRights()));
            appDetail.setTitle(new Title(entry.getTitle()));
            appDetail.setLink(new Link(new Attributes___(entry.getLink())));
            appDetail.setId(new Id(new Attributes____(String.valueOf(entry.getId()))));
            appDetail.setImArtist(new ImArtist(entry.getImArtist()));
            appDetail.setCategory(new Category(new Attributes______(entry.getCategory())));
            appDetail.setImReleaseDate(new ImReleaseDate(new Attributes_______(entry.getImReleaseDate())));
            apps.add(appDetail);
        }
        return apps;
    }

    public static void deleteEntryTable(){
        SQLite.delete(Entry.class).execute();
    }
}
