package com.randyychan.rxandroid.googleapi;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rchan on 4/26/15.
 */
public class MapAddress {

    public List<Result> results = new ArrayList<Result>();
    public String status;

    public static class Result {

        @SerializedName("formatted_address")
        public String formattedAddress;

        @SerializedName("place_id")
        public String placeId;

    }

}

