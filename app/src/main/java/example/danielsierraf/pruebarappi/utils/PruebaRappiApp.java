package example.danielsierraf.pruebarappi.utils;

import android.app.Application;
import android.content.Context;

import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

/**
 * Created by danielsierraf on 6/12/16.
 */
public class PruebaRappiApp extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        FlowManager.init(new FlowConfig.Builder(this).build());
        context = getApplicationContext();
    }

    public static Context getInstance() { return context; }
}
