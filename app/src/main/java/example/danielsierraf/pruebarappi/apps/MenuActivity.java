package example.danielsierraf.pruebarappi.apps;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.sqlite.SQLiteConstraintException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ProgressBar;

import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.From;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import example.danielsierraf.pruebarappi.R;
import example.danielsierraf.pruebarappi.api.PublicService;
import example.danielsierraf.pruebarappi.api.RestClientPublic;
import example.danielsierraf.pruebarappi.api.classes.AppDetail;
import example.danielsierraf.pruebarappi.api.classes.Response_;
import example.danielsierraf.pruebarappi.model.Entry;
import example.danielsierraf.pruebarappi.model.Entry_Table;
import example.danielsierraf.pruebarappi.utils.Helper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by danielsierraf on 6/11/16.
 */
public class MenuActivity extends AppCompatActivity {
    @BindView(R.id.card_recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    private static final String TAG = "MenuActivity";
    private static final PublicService publicService = new RestClientPublic().getPublicService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        FlowManager.init(new FlowConfig.Builder(this)
                .openDatabasesOnInit(true).build());
//        progressBar.setVisibility(View.VISIBLE);
        publicService.getResponse().enqueue(new Callback<Response_>() {
            @Override
            public void onResponse(Call<Response_> call, Response<Response_> response) {
                if (response != null && response.isSuccessful()){
                    List<AppDetail> entry = response.body().getFeed().getEntry();
                    initViews(entry);
                    insertToDatabase(entry);
                }
//                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<Response_> call, Throwable t) {
                Log.d(TAG, "Service Failed");
//                progressBar.setVisibility(View.GONE);
            }
        });
    }

    private void initViews(final List<AppDetail> entry){
        mRecyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager;
        if (Helper.isTabletSize(this)){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            layoutManager = new GridLayoutManager(this, 3);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            layoutManager = new LinearLayoutManager(this);
        }
        mRecyclerView.setLayoutManager(layoutManager);

//        final ArrayList pictures = prepareData();
        DataAdapter adapter = new DataAdapter(entry, this);

        mRecyclerView.setAdapter(adapter);

        mRecyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            GestureDetector gestureDetector = new GestureDetector(getApplicationContext(),
                    new GestureDetector.SimpleOnGestureListener() {

                        @Override public boolean onSingleTapUp(MotionEvent e) {
                            return true;
                        }

                    });
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

                View child = rv.findChildViewUnder(e.getX(), e.getY());
                if(child != null && gestureDetector.onTouchEvent(e)) {
                    int position = rv.getChildAdapterPosition(child);
//                    Toast.makeText(getApplicationContext(), entry.get(position).getImName().getLabel(),
//                            Toast.LENGTH_SHORT).show();
                    AppDetail appDetail = entry.get(position);
                    Intent intent = new Intent(MenuActivity.this, AppDetailActivity.class);
                    intent.putExtra(AppDetailActivity.EXTRA_IMAGE, appDetail.getImImage().get(2)
                            .getLabel());
                    intent.putExtra(AppDetailActivity.EXTRA_IMARTIST, appDetail.getImArtist()
                            .getLabel());
                    intent.putExtra(AppDetailActivity.EXTRA_IMNAME, appDetail.getImName().getLabel());
                    intent.putExtra(AppDetailActivity.EXTRA_APP_TITLE, appDetail.getTitle().getLabel());
                    intent.putExtra(AppDetailActivity.EXTRA_PRICE, appDetail.getImPrice().getLabel());
                    intent.putExtra(AppDetailActivity.EXTRA_SUMMARY, appDetail.getSummary()
                            .getLabel());
                    intent.putExtra(AppDetailActivity.EXTRA_CATEGORY, appDetail.getCategory()
                            .getAttributes().getLabel());
                    intent.putExtra(AppDetailActivity.EXTRA_RELEASE_DATE, appDetail.getImReleaseDate()
                            .getAttributes().getLabel());
                    intent.putExtra(AppDetailActivity.EXTRA_LINK, appDetail.getLink().getAttributes()
                            .getHref());
                    intent.putExtra(AppDetailActivity.EXTRA_RIGHTS, appDetail.getRights().getLabel());
                    startActivity(intent);
                }

                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
    }

    private void insertToDatabase(List<AppDetail> appDetails){
        Log.d(TAG, "Respuesta SQLITE: "+SQLite.select(Entry_Table.imName).from(Entry.class));
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
}
