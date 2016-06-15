package example.danielsierraf.pruebarappi.apps;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.widget.Toast;

/**
 * Created by danielsierraf on 6/14/16.
 */
public class NetworkStatusReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        if (action.equals(WifiManager.NETWORK_STATE_CHANGED_ACTION)){
            int extraWifiState = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE ,
                    WifiManager.WIFI_STATE_UNKNOWN);

            switch(extraWifiState){
                case WifiManager.WIFI_STATE_DISABLED:
                    Toast.makeText(context, "WIFI STATE DISABLED", Toast.LENGTH_SHORT).show();
                    break;
                case WifiManager.WIFI_STATE_DISABLING:
                    Toast.makeText(context, "WIFI STATE DISABLING", Toast.LENGTH_SHORT).show();
                    break;
                case WifiManager.WIFI_STATE_ENABLED:
                    Toast.makeText(context, "WIFI STATE ENABLED", Toast.LENGTH_SHORT).show();
                    break;
                case WifiManager.WIFI_STATE_ENABLING:
                    Toast.makeText(context, "WIFI STATE ENABLING", Toast.LENGTH_SHORT).show();
                    break;
                case WifiManager.WIFI_STATE_UNKNOWN:
                    Toast.makeText(context, "WIFI STATE UNKNOWN", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }
}
