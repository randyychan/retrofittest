package com.randyychan.rxandroid;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.randyychan.rxandroid.googleapi.GoogleMapEndpoints;
import com.randyychan.rxandroid.googleapi.RetrofitService;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class TestActivity extends ActionBarActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        textView = (TextView) findViewById(R.id.text);

        GoogleMapEndpoints googleMapEndpoints = RetrofitService.getGoogleMapsInstance();

        googleMapEndpoints.reverseGeocode("37.392623,-122.080713")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mapAddress -> {
                    textView.setText(mapAddress.results.get(0).formattedAddress);
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_test, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
