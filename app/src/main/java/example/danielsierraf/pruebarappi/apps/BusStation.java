package example.danielsierraf.pruebarappi.apps;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

/**
 * Created by VCHI on 15/6/16.
 */
public class BusStation {
    private static Bus bus;

    public synchronized static Bus getBus(){

        if (bus == null){
            bus = new Bus(ThreadEnforcer.ANY);
        }
        return bus;
    }
}
