package example.danielsierraf.pruebarappi.apps;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.easyandroidanimations.library.Animation;
import com.easyandroidanimations.library.AnimationListener;
import com.easyandroidanimations.library.BounceAnimation;
import com.easyandroidanimations.library.FadeOutAnimation;
import com.easyandroidanimations.library.FlipHorizontalAnimation;
import com.easyandroidanimations.library.PuffInAnimation;
import com.easyandroidanimations.library.RotationAnimation;
import com.easyandroidanimations.library.SlideInAnimation;
import com.easyandroidanimations.library.SlideOutAnimation;
import com.easyandroidanimations.library.SlideOutUnderneathAnimation;

import butterknife.BindView;
import butterknife.ButterKnife;
import example.danielsierraf.pruebarappi.R;
import example.danielsierraf.pruebarappi.api.classes.ImName;

/**
 * Created by danielsierraf on 6/12/16.
 */
public class AppDetailActivity extends AppCompatActivity {

    public static String EXTRA_TITLE = "example.danielsierraf.pruebarappi.apps.EXTRA_TITLE";
    public static String EXTRA_IMAGE = "example.danielsierraf.pruebarappi.apps.EXTRA_IMAGE";
    public static String EXTRA_IMARTIST = "example.danielsierraf.pruebarappi.apps.EXTRA_IMARTIST";
    public static String EXTRA_IMNAME = "example.danielsierraf.pruebarappi.apps.EXTRA_IMNAME";
    public static String EXTRA_APP_TITLE = "example.danielsierraf.pruebarappi.apps.EXTRA_APP_TITLE";
    public static String EXTRA_PRICE = "example.danielsierraf.pruebarappi.apps.EXTRA_PRICE";
    public static String EXTRA_SUMMARY = "example.danielsierraf.pruebarappi.apps.EXTRA_SUMMARY";
    public static String EXTRA_CATEGORY = "example.danielsierraf.pruebarappi.apps.EXTRA_CATEGORY";
    public static String EXTRA_RELEASE_DATE = "example.danielsierraf.pruebarappi.apps" +
            ".EXTRA_RELEASE_DATE";
    public static String EXTRA_LINK = "example.danielsierraf.pruebarappi.apps.EXTRA_LINK";
    public static String EXTRA_RIGHTS = "example.danielsierraf.pruebarappi.apps.EXTRA_RIGHTS";

    @BindView(R.id.imImage)
    ImageView imImage;
    @BindView(R.id.imArtist)
    TextView imArtist;
    @BindView(R.id.imName)
    TextView imName;
    @BindView(R.id.app_title_tv)
    TextView app_title_tv;
    @BindView(R.id.btn_price)
    TextView btn_price;
    @BindView(R.id.summary)
    TextView summary;
    @BindView(R.id.category)
    TextView category;
    @BindView(R.id.last_updated)
    TextView last_updated;
    @BindView(R.id.rights)
    TextView rights;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_detail);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        Glide.with(this).load(intent.getStringExtra(EXTRA_IMAGE)).override(320, 320).into(imImage);
        imArtist.setText(intent.getStringExtra(EXTRA_IMARTIST));
        imName.setText(intent.getStringExtra(EXTRA_IMNAME));
        app_title_tv.setText(intent.getStringExtra(EXTRA_APP_TITLE));
        btn_price.setText(intent.getStringExtra(EXTRA_PRICE));
        summary.setText(intent.getStringExtra(EXTRA_SUMMARY));
        category.setText(intent.getStringExtra(EXTRA_CATEGORY));
        last_updated.setText(intent.getStringExtra(EXTRA_RELEASE_DATE));
        rights.setText(intent.getStringExtra(EXTRA_RIGHTS));

        new FlipHorizontalAnimation(imImage).setListener(
                new AnimationListener() {

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        new BounceAnimation(imArtist).setBounceDistance(50).setNumOfBounces(3).setDuration(500)
                                .animate();
                    }
                }).animate();

        new RotationAnimation(btn_price).setPivot(RotationAnimation.PIVOT_TOP_LEFT).setListener(new AnimationListener() {
            @Override
            public void onAnimationEnd(Animation animation) {
                new SlideInAnimation(summary).setDirection(Animation.DIRECTION_UP).animate();
            }
        }).animate();

        new PuffInAnimation(imName).animate();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        new FadeOutAnimation(imImage).animate();
        new SlideOutUnderneathAnimation(summary).setDirection(Animation.DIRECTION_RIGHT).animate();
        new SlideOutAnimation(btn_price).setDirection(Animation.DIRECTION_LEFT).animate();
    }
}
