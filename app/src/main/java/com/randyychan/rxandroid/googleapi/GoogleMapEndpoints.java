package com.randyychan.rxandroid.googleapi;

import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by rchan on 4/26/15.
 */
public interface GoogleMapEndpoints {
    @GET("/maps/api/geocode/json")
    Observable<MapAddress> reverseGeocode(@Query("latlng") String latlng);
}
