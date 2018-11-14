/*
 * Copyright (c) Developed by John Alves at 2018/10/31.
 */

package spyrecorder.com.googlemaps.Maps;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;
import java.util.Locale;

import spyrecorder.com.googlemaps.R;

public class GoogleMapsInitHelper {

    private static GoogleMap mMap;
    private static LatLng latLng;

    @SuppressLint("MissingPermission")
    public static GoogleMap initGoogleMaps(final Activity activity, GoogleMap googlemap) {

        mMap = googlemap;
        mMap.clear();
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        mMap.getUiSettings().isMyLocationButtonEnabled();
        mMap.setMyLocationEnabled(true);
        mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
//        int height = activity.getResources().getDisplayMetrics().heightPixels;
//        int k = (int) (height * (75 / 100.0));
//        mMap.setPadding(0, k, 0, 0);

        mMap.setOnMyLocationButtonClickListener(new GoogleMap.OnMyLocationButtonClickListener() {
            @Override
            public boolean onMyLocationButtonClick() {
                return false;
            }
        });




//        lm = (LocationManager)
//                activity.getSystemService(LOCATION_SERVICE);
//
//        lm.requestLocationUpdates(
//                LocationManager.NETWORK_PROVIDER,
//                0,
//                0,
//                servico_localizacao
//        );

//        if (mMap != null) {
//            mMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
//
//                @Override
//                public void onMyLocationChange(Location arg0) {
//                    mMap.addMarker(new MarkerOptions().position(new LatLng(arg0.getLatitude(), arg0.getLongitude())).title("It's Me!"));
//                }
//            });
//        }

        return mMap;
    }

    private static LocationListener servico_localizacao = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {

            Double latitude = location.getLatitude();
            Double longitude = location.getLongitude();

            latLng = new LatLng(latitude, longitude);
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(latLng);

            mMap.addMarker(markerOptions).remove();
            setMapsZoom(mMap, latLng);

            Log.w("Localização", location.getLatitude() + " " + location.getLongitude());

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };

    public static LatLng getLatLng() {
        return latLng;
    }

    //==============================================================================================================================================================================================================
    //
    // GoogleMaps Util's
    //
    //==============================================================================================================================================================================================================
    // For Zoom Type
    // 1: World
    // 5: Landmass/continent
    // 10: City
    // 15: Streets
    // 20: Buildings

    public static void setMapsZoom(GoogleMap mMap, LatLng latLng) {
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
    }

    public static boolean setMapsZoom(GoogleMap mMap, LatLng latLng, boolean fistLoad) {
        if (fistLoad) {
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
        }
        return false;
    }

    public static void setMapsBoundsZoom(Activity activity, GoogleMap mMap, LatLng latLng) {

        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        builder.include(latLng);
        LatLngBounds bounds = builder.build();
        int width = activity.getResources().getDisplayMetrics().widthPixels;
        int height = activity.getResources().getDisplayMetrics().heightPixels;
        int padding = (int) (width * 0.30); // offset from edges of the map 30% of screen
        CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, width, height, padding);
        mMap.animateCamera(cu);

    }

    @SuppressLint("MissingPermission")
    public static void clearGoogleMaps(GoogleMap mMap) {
        mMap.clear();
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        mMap.getUiSettings().isMyLocationButtonEnabled();
        mMap.setMyLocationEnabled(true);
//        LatLng latLng = new LatLng(startLatitude, startLongitude);
//        MarkerOptions markerOptions = new MarkerOptions();
//        markerOptions.position(latLng);
//
//        mMap.addMarker(markerOptions);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
    }


//    /**
//     * Gets distance.
//     *
//     * @param from the from
//     * @param to   the to
//     * @return the distance
//     */
//    public static float getDistance(Activity activity, Location from, Location to) {
//        android.location.Location locationA = new android.location.Location("Point A");
//        locationA.setLatitude(from.getLatitude());
//        locationA.setLongitude(from.getLongitude());
//        android.location.Location locationB = new android.location.Location("Point B");
//        locationA.setLatitude(to.getLatitude());
//        locationA.setLongitude(to.getLongitude());
//        return getDistance(locationA, locationB);
//    }
//
//    /**
//     * Gets distance.
//     *
//     * @param from the from
//     * @param to   the to
//     * @return the distance
//     */
//    public static float getDistance(Location from, android.location.Location to) {
//        android.location.Location locationA = new android.location.Location("Point A");
//        locationA.setLatitude(from.getLat());
//        locationA.setLongitude(from.getLng());
//        return getDistance(locationA, to);
//    }
//
//    /**
//     * Gets distance.
//     *
//     * @param from the from
//     * @param to   the to
//     * @return the distance
//     */
//    public static float getDistance(android.location.Location from, android.location.Location to) {
//        return from.distanceTo(to);
//    }

    @SuppressLint("LongLogTag")
    private String getCompleteAddressString(Activity activity, double LATITUDE, double LONGITUDE) {

        String strAdd = "";

        Geocoder geocoder = new Geocoder(activity, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(LATITUDE,
                    LONGITUDE, 1);

            if (addresses != null) {

                Address returnedAddress = addresses.get(0);

                StringBuilder strReturnedAddress = new StringBuilder("");

                for (int i = 0; i < returnedAddress.getMaxAddressLineIndex(); i++) {

                    strReturnedAddress
                            .append(returnedAddress.getAddressLine(i)).append(
                            ",");
                }

                strAdd = strReturnedAddress.toString();

                Log.w("My Current loction address", "" + strReturnedAddress.toString());
            } else {
                Log.w("My Current loction address", "No Address returned!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.w("My Current loction address", "Canont get Address!");
        }
        return strAdd;
    }

    //==============================================================================================================================================================================================================
    //
    // PlaceAutocompleteFragment Util's
    //
    //==============================================================================================================================================================================================================

    public static AutocompleteFilter getAutocompleteFilter() {
        return new AutocompleteFilter.Builder()
                .setTypeFilter(AutocompleteFilter.TYPE_FILTER_NONE)
                .setCountry("BR")
                .build();
    }

    public static void showPlaceData(Place place) {
        Log.w("", place.toString());
    }

    public static void setStyleAutoCompleteEditText(PlaceAutocompleteFragment placeAutocompleteFragment) {
        ((EditText) placeAutocompleteFragment.getView().findViewById(R.id.place_autocomplete_search_input)).setTextSize(14.0f);
        ((EditText) placeAutocompleteFragment.getView().findViewById(R.id.place_autocomplete_search_input)).setPadding(0, 0, 0, 0);
        ((EditText) placeAutocompleteFragment.getView().findViewById(R.id.place_autocomplete_search_input)).setFitsSystemWindows(true);
    }

    public static void setVisiblePlaceAutocompleteFragment(boolean status, PlaceAutocompleteFragment[] autocompleteFragments) {
        for (PlaceAutocompleteFragment autocompleteFragment : autocompleteFragments) {
            autocompleteFragment.getView().setVisibility(getViewVisibility(status));
        }
    }

    private static int getViewVisibility(boolean status) {
        return status ? View.VISIBLE : View.GONE;
    }
}
