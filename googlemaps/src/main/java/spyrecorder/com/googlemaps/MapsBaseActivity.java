/*
 * Copyright (c) Developed by John Alves at 2018/10/30.
 */

package spyrecorder.com.googlemaps;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import java.util.ArrayList;
import java.util.List;
import spyrecorder.com.googlemaps.Broadcast.BroadcastUtils;
import spyrecorder.com.googlemaps.Locations.GoogleApiLocationManager;
import spyrecorder.com.googlemaps.Maps.GoogleMapsInitHelper;

public abstract class MapsBaseActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMapLoadedCallback {

    //Init
    public static Activity activity;

    //Location
    public static GoogleApiClient googleApiClient;
    private static GoogleMap mMap;
    public static LatLng latLngStart = new LatLng(0.0, 0.0);

    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

        activity = MapsBaseActivity.this;

        initVars();
        setUpGClient();
        initActions();
        initLocationListenner();

    }

    private void initVars() {

    }

    private void initActions() {
        initGoogleMaps();
    }

    //==============================================================================================
    //
    // Broadcast
    //
    //==============================================================================================

    private void initLocationListenner() {
        BroadcastUtils bUtil = new BroadcastUtils(activity);
        bUtil.verifieLocationEnabled();
    }

    //==============================================================================================
    //
    // GoogleMaps
    //
    //==============================================================================================

    @SuppressLint("ResourceType")
    private void initGoogleMaps() {
        //        ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMapAsync(this);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);

        View mapView = mapFragment.getView();
        if (mapView != null && mapView.findViewById(1) != null) {
            // Get the button view
            View locationButton = ((View) mapView.findViewById(1).getParent()).findViewById(2);
            // and next place it, on bottom right (as Google Maps app)
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams)
                    locationButton.getLayoutParams();
            // position on right bottom
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP, 0);
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
            layoutParams.setMargins(0, 0, 30, 30);
        }
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = GoogleMapsInitHelper.initGoogleMaps(activity, googleMap);
    }

    @Override
    public void onMapLoaded() {

    }

    /**
     * Run the specific code.
     */
//    protected abstract void startDemo();
    public static GoogleMap getMap() {
        return mMap;
    }

    public static LatLng getLatLngInitApp() {
        return latLngStart;
    }

    //==============================================================================================
    //
    //
    //
    //==============================================================================================

    private synchronized void setUpGClient() {
        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, 0, GoogleApiLocationManager.onConnectionFailedListener)
                .addConnectionCallbacks(GoogleApiLocationManager.connectionCallbacks)
                .addOnConnectionFailedListener(GoogleApiLocationManager.onConnectionFailedListener)
                .addApi(LocationServices.API)
                .build();
        googleApiClient.connect();
    }

    //==============================================================================================
    //
    //
    //
    //==============================================================================================

    public static void checkPermissions() {
        int permissionLocation = ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION);
        List<String> listPermissionsNeeded = new ArrayList<>();
        if (permissionLocation != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.ACCESS_FINE_LOCATION);
            if (!listPermissionsNeeded.isEmpty()) {
                ActivityCompat.requestPermissions(activity, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), 10);
            }
        } else {
            GoogleApiLocationManager.getMyLocation(activity);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        int permissionLocation = ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION);
        if (permissionLocation == PackageManager.PERMISSION_GRANTED) {
            GoogleApiLocationManager.getMyLocation(activity);
        } else if (permissionLocation == PackageManager.PERMISSION_DENIED) {
            Log.w("error", "location");
        }
    }
}
