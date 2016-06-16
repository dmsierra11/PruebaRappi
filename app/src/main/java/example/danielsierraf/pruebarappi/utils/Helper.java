package example.danielsierraf.pruebarappi.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import example.danielsierraf.pruebarappi.R;

/**
 * Created by danielsierraf on 6/11/16.
 */
public class Helper {
    private static final String TAG = "Helper";

    public static boolean isTabletSize(Context context){
        boolean tabletSize = context.getResources().getBoolean(R.bool.is_tablet);
        String msg = tabletSize? "Tablet size": "Smartphone size";
        Log.d(TAG, "Size: "+msg);
//        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
        return tabletSize;
    }

    public static boolean isNetworkAvailable(Context ctx) {
        ConnectivityManager cm;
        NetworkInfo info = null;
        try
        {
            cm = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
            info = cm.getActiveNetworkInfo();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        if (info != null && info.isConnected())
            return true;
        else
            return false;
    }
}
