package example.danielsierraf.pruebarappi.apps;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import example.danielsierraf.pruebarappi.utils.Helper;

/**
 * Created by VCHI on 15/6/16.
 */
public class NetworkConnectivityReceiver extends BroadcastReceiver {


    private final static String TAG = "NetworkConnectivity";

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        if (action.equals(ConnectivityManager.CONNECTIVITY_ACTION)){
            BusStation.getBus().post(Helper.isNetworkAvailable(context));
        }
    }
}
