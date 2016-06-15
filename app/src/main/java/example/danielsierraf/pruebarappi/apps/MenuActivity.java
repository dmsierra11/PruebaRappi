package example.danielsierraf.pruebarappi.apps;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import example.danielsierraf.pruebarappi.R;
import example.danielsierraf.pruebarappi.api.PublicService;
import example.danielsierraf.pruebarappi.api.RestClientPublic;
import example.danielsierraf.pruebarappi.api.classes.AppDetail;
import example.danielsierraf.pruebarappi.api.classes.Response_;
import example.danielsierraf.pruebarappi.model.AppDatabase;
import example.danielsierraf.pruebarappi.utils.Helper;
import example.danielsierraf.pruebarappi.utils.PruebaRappiApp;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by danielsierraf on 6/11/16.
 */
public class MenuActivity extends AppCompatActivity {
    @BindView(R.id.categories)
    LinearLayout mCategories;
    @BindView(R.id.card_recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.btn_all_categories)
    Button btn_all_categories;

    private static final String TAG = "MenuActivity";
    private static final PublicService publicService = new RestClientPublic().getPublicService();
    List<AppDetail> entry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ButterKnife.bind(this);
        FlowManager.init(new FlowConfig.Builder(this)
                .openDatabasesOnInit(true).build());
        getAppsDataFromService();
        registerReceiver(new NetworkStatusReceiver(),
                new IntentFilter(WifiManager.NETWORK_STATE_CHANGED_ACTION));
    }

    private void getAppsDataFromService(){
        progressBar.setVisibility(View.VISIBLE);
        publicService.getResponse().enqueue(new Callback<Response_>() {
            @Override
            public void onResponse(Call<Response_> call, Response<Response_> response) {
                progressBar.setVisibility(View.GONE);
                if (response != null && response.isSuccessful()){
                    entry = response.body().getFeed().getEntry();
                    if (entry != null){
                        initViews();
                        AppDatabase.deleteEntryTable();
                        AppDatabase.insertEntryToDatabase(entry);
                    }
                }
            }

            @Override
            public void onFailure(Call<Response_> call, Throwable t) {
                Log.d(TAG, "Service Failed");
                progressBar.setVisibility(View.GONE);
                entry = AppDatabase.getEntryFromDatabase();
                if (entry != null)
                    initViews();
            }
        });
    }

    private void initViews(){
        btn_all_categories.setVisibility(View.VISIBLE);

        //Categories
        List<String> categories = getCategories(entry);
        addCategoryButtons(categories);

        //List of apps
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
        DataAdapter adapter = new DataAdapter(entry, this);
        mRecyclerView.setAdapter(adapter);

        addOnItemTouchListener();
    }

    private void addOnItemTouchListener(){
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

    protected List<String> getCategories(List<AppDetail> apps){
        List<String> categories = new ArrayList<>();
        for (AppDetail appDetail: apps){
            String category = appDetail.getCategory().getAttributes().getLabel();
            if (!categories.contains(category))
                categories.add(category);
        }
        return categories;
    }

    private void addCategoryButtons(List<String> categories){
        for (final String category: categories){
            Button newCategory = new Button(this);
            newCategory.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT));
            newCategory.setText(category);
            newCategory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    List<AppDetail> entry = getEntryByCategory(category);
                    DataAdapter adapter = new DataAdapter(entry, PruebaRappiApp.getInstance());
                    mRecyclerView.swapAdapter(adapter, true);

                }
            });
            mCategories.addView(newCategory);
        }
    }

    protected List<AppDetail> getEntryByCategory(String category){
        List<AppDetail> entry = new ArrayList<>();
        if (this.entry != null){
            for (AppDetail appDetail: this.entry){
                String app_category = appDetail.getCategory().getAttributes().getLabel();
                if (app_category.equals(category))
                    entry.add(appDetail);
            }
        }
        return entry;
    }

    @OnClick(R.id.btn_all_categories)
    public void listAllCategories(){
        if (entry != null){
            DataAdapter adapter = new DataAdapter(this.entry, this);
            mRecyclerView.swapAdapter(adapter, true);
        }
        AppDatabase.getEntryFromDatabase();
    }
}
