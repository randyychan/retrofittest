package com.randyychan.rxandroid.googleapi;

import retrofit.RestAdapter;

/**
 * Created by rchan on 4/26/15.
 */
public class RetrofitService {

    private static GoogleMapEndpoints googleMaps;

    public synchronized static GoogleMapEndpoints getGoogleMapsInstance() {
        if (googleMaps == null) {
            return createGoogleMaps();
        }

        return googleMaps;
    }

    private static GoogleMapEndpoints createGoogleMaps() {
        RestAdapter restAdapter =
                new RestAdapter.Builder()
                .setEndpoint("https://maps.googleapis.com")
                .build();

        googleMaps = restAdapter.create(GoogleMapEndpoints.class);
        return googleMaps;
    }
}
