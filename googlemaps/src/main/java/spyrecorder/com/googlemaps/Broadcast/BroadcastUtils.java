/*
 * Copyright (c) Developed by John Alves at 2018/10/31.
 */

package spyrecorder.com.googlemaps.Broadcast;

import android.content.Context;
import android.location.LocationManager;
import android.util.Log;

import spyrecorder.com.googlemaps.BuildConfig;
import spyrecorder.com.googlemaps.Locations.GoogleApiLocationManager;

public class BroadcastUtils {

    private Context context;

    public BroadcastUtils(Context context) {
        this.context = context;
    }

    public void verifieLocationEnabled() {
        LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        boolean gps_enabled = false;
        boolean network_enabled = false;

        try {
            if (lm != null) {
                gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
                network_enabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
                log("Location: Gps:" + gps_enabled + ", Network:" + network_enabled);
            }
        } catch (Exception ignored) {
        }

        if (!gps_enabled && !network_enabled) {
            // notify user
            GoogleApiLocationManager.getMyLocation(context);
        }

        if (locationCallback != null) {
            locationCallback.NewLocation(gps_enabled, network_enabled);
        }

    }

    //==============================================================================================
    //
    // Utils
    //
    //==============================================================================================
    public static void log(String s) {
        if (getDebugStatus()) {
            Log.w("broadcastUtils Alert", s);
        }
    }

    private static boolean getDebugStatus() {
        return BuildConfig.DEBUG;
    }

    //==============================================================================================
    //
    // @Interface
    //
    //==============================================================================================

    LocationCallback locationCallback;

    public void setLocationCallback(LocationCallback locationCallback) {
        this.locationCallback = locationCallback;
    }

    public interface LocationCallback {
        void NewLocation(boolean gps_enabled, boolean network_enabled);
    }

}
