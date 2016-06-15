package example.danielsierraf.pruebarappi.apps;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by VCHI on 15/6/16.
 */
public class NetworkConnectivityReceiver extends BroadcastReceiver {


    private final static String TAG = "NetworkConnectivity";

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        if (action.equals(ConnectivityManager.CONNECTIVITY_ACTION)){
            BusStation.getBus().post(isNetworkAvailable(context));
        }
    }

    public boolean isNetworkAvailable(Context ctx) {
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
