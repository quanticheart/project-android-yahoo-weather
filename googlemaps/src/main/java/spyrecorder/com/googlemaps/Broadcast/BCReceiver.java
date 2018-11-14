/*
 * Copyright (c) Developed by John Alves at 2018/10/31.
 */

package spyrecorder.com.googlemaps.Broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;

public class BCReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals(LocationManager.PROVIDERS_CHANGED_ACTION)) {
            BroadcastUtils bUtil = new BroadcastUtils(context);
            bUtil.verifieLocationEnabled();
        }

    }
}
