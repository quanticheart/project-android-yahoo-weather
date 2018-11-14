///*
// *
// *  *                                     /@
// *  *                      __        __   /\/
// *  *                     /==\      /  \_/\/
// *  *                   /======\    \/\__ \__
// *  *                 /==/\  /\==\    /\_|__ \
// *  *              /==/    ||    \=\ / / / /_/
// *  *            /=/    /\ || /\   \=\/ /
// *  *         /===/   /   \||/   \   \===\
// *  *       /===/   /_________________ \===\
// *  *    /====/   / |                /  \====\
// *  *  /====/   /   |  _________    /      \===\
// *  *  /==/   /     | /   /  \ / / /         /===/
// *  * |===| /       |/   /____/ / /         /===/
// *  *  \==\             /\   / / /          /===/
// *  *  \===\__    \    /  \ / / /   /      /===/   ____                    __  _         __  __                __
// *  *    \==\ \    \\ /____/   /_\ //     /===/   / __ \__  ______  ____ _/ /_(_)____   / / / /__  ____ ______/ /_
// *  *    \===\ \   \\\\\\\/   ///////     /===/  / / / / / / / __ \/ __ `/ __/ / ___/  / /_/ / _ \/ __ `/ ___/ __/
// *  *      \==\/     \\\\/ / //////       /==/  / /_/ / /_/ / / / / /_/ / /_/ / /__   / __  /  __/ /_/ / /  / /_
// *  *      \==\     _ \\/ / /////        |==/   \___\_\__,_/_/ /_/\__,_/\__/_/\___/  /_/ /_/\___/\__,_/_/   \__/
// *  *        \==\  / \ / / ///          /===/
// *  *        \==\ /   / / /________/    /==/
// *  *          \==\  /               | /==/
// *  *          \=\  /________________|/=/
// *  *            \==\     _____     /==/
// *  *           / \===\   \   /   /===/
// *  *          / / /\===\  \_/  /===/
// *  *         / / /   \====\ /====/
// *  *        / / /      \===|===/
// *  *        |/_/         \===/
// *  *                       =
// *  *
// *  * Copyright(c) Developed by John Alves at 2018/$today.mouth/13 at 1:17:31 for quantic heart studios
// *
// */
//
//package quanticheart.com.yahooweather.Broadcast;
//
//import android.Manifest;
//import android.content.Context;
//import android.content.IntentSender;
//import android.content.pm.PackageManager;
//import android.location.Location;
//import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.support.v4.content.ContextCompat;
//
//import quanticheart.com.yahooweather.MainActivity;
//
//
//public class GoogleApiLocationManager {
//
//    private static GoogleApiClient googleApiClient = null;
//    private static Location mylocation = null;
//
//    public static void getMyLocation(final Context context) {
//
//        googleApiClient = MainActivity.googleApiClient;
//        mylocation = MainActivity.mylocation;
//
//        if (googleApiClient != null) {
//            if (googleApiClient.isConnected()) {
//                int permissionLocation = ContextCompat.checkSelfPermission(context,
//                        Manifest.permission.ACCESS_FINE_LOCATION);
//                if (permissionLocation == PackageManager.PERMISSION_GRANTED) {
//                    mylocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
//                    LocationRequest locationRequest = new LocationRequest();
//                    locationRequest.setInterval(3000);
//                    locationRequest.setFastestInterval(3000);
//                    locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
//
//                    LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().addLocationRequest(locationRequest);
//                    builder.setAlwaysShow(true);
//
//                    LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, locationListener);
//                    PendingResult<LocationSettingsResult> result = LocationServices.SettingsApi.checkLocationSettings(googleApiClient, builder.build());
//                    result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
//
//                        @Override
//                        public void onResult(LocationSettingsResult result) {
//                            final Status status = result.getStatus();
//                            switch (status.getStatusCode()) {
//                                case LocationSettingsStatusCodes.SUCCESS:
//                                    // All location settings are satisfied.
//                                    // You can initialize location requests here.
//                                    int permissionLocation = ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION);
//                                    if (permissionLocation == PackageManager.PERMISSION_GRANTED) {
//                                        mylocation = LocationServices.FusedLocationApi
//                                                .getLastLocation(googleApiClient);
//                                    }
//                                    break;
//                                case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
//                                    // Location settings are not satisfied.
//                                    // But could be fixed by showing the user a dialog.
//                                    try {
//                                        // Show the dialog by calling startResolutionForResult(),
//                                        // and check the result in onActivityResult().
//                                        // Ask to turn on GPS automatically
//                                        status.startResolutionForResult(MainActivity.activity, 10);
//                                    } catch (IntentSender.SendIntentException e) {
//                                        // Ignore the error.
//                                    }
//                                    break;
//                                case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
//                                    // Location settings are not satisfied.
//                                    // However, we have no way
//                                    // to fix the
//                                    // settings so we won't show the dialog.
//                                    // finish();
//                                    break;
//                            }
//                        }
//                    });
//                }
//            }
//        }
//    }
//
//    public static GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener = new GoogleApiClient.OnConnectionFailedListener() {
//        @Override
//        public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
//
//        }
//    };
//
//    public static GoogleApiClient.ConnectionCallbacks connectionCallbacks = new GoogleApiClient.ConnectionCallbacks() {
//        @Override
//        public void onConnected(@Nullable Bundle bundle) {
//            MainActivity.checkPermissions();
//        }
//
//        @Override
//        public void onConnectionSuspended(int i) {
//
//        }
//    };
//
//    private static com.google.android.gms.location.LocationListener locationListener = new com.google.android.gms.location.LocationListener() {
//        @Override
//        public void onLocationChanged(Location location) {
//            mylocation = location;
//            if (mylocation != null) {
//                Double latitude = mylocation.getLatitude();
//                Double longitude = mylocation.getLongitude();
//                MainActivity.labelLat.setText("Latitude : " + latitude);
//                MainActivity.labelLong.setText("Longitude : " + longitude);
//            }
//        }
//    };
//
//
//}
