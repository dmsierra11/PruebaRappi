package example.danielsierraf.pruebarappi.apps;

import android.content.Intent;
import android.content.pm.ActivityInfo;
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

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import example.danielsierraf.pruebarappi.R;
import example.danielsierraf.pruebarappi.api.PublicService;
import example.danielsierraf.pruebarappi.api.RestClientPublic;
import example.danielsierraf.pruebarappi.api.classes.AppDetail;
import example.danielsierraf.pruebarappi.api.classes.Response_;
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
//        progressBar.setVisibility(View.VISIBLE);
        publicService.getResponse().enqueue(new Callback<Response_>() {
            @Override
            public void onResponse(Call<Response_> call, Response<Response_> response) {
                if (response != null && response.isSuccessful())
                    initViews(response.body().getFeed().getEntry());
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

//    private ArrayList<String> prepareData(){
//        ArrayList<String> filePaths = new ArrayList<>();
//        for (int i=0; i<android_image_urls.length; i++){
//            filePaths.add(android_image_urls[i]);
//        }
//        return filePaths;
//    }
}
