package com.hh.gridview_recyclerview.activity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.widget.EditText;

import com.hh.gridview_recyclerview.R;

public class LocationActivity extends BaseActivity {
    LocationManager lm;
    EditText et;
    LocationListener ll = new LocationListener() {
        public void onLocationChanged(Location location) {
            updateView(location);
        }

        @Override
        public void onProviderDisabled(String provider) {
            updateView(null);
        }

        @Override
        public void onProviderEnabled(String provider) {
            if (ContextCompat.checkSelfPermission(LocationActivity.this, permissions[0]) != PackageManager.PERMISSION_GRANTED) {
                Location l = lm.getLastKnownLocation(provider);
                updateView(l);
            }
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }
    };
    String[] permissions = {Manifest.permission.LOCATION_HARDWARE, Manifest.permission.ACCESS_COARSE_LOCATION};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locationphone);
        et = (EditText) findViewById(R.id.et);
        lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        String bestProvider = lm.getBestProvider(getCriteria(), true);

        if (ContextCompat.checkSelfPermission(this, permissions[0]) != PackageManager.PERMISSION_GRANTED) {
            Location l = lm.getLastKnownLocation(bestProvider);
            updateView(l);
            lm.requestLocationUpdates(bestProvider, 5000, 8, ll);
        }
    }


    private Criteria getCriteria() {
        Criteria c = new Criteria();
        c.setAccuracy(Criteria.ACCURACY_COARSE);
        c.setSpeedRequired(false);
        c.setCostAllowed(false);
        c.setBearingRequired(false);
        c.setAltitudeRequired(false);
        c.setPowerRequirement(Criteria.POWER_LOW);
        return c;
    }

    public void updateView(Location newLocation) {
        if (newLocation != null) {
            et.setText("您现在的位置是\n纬度：");
            et.append(String.valueOf(newLocation.getLatitude()));
            et.append("\n经度：");
            et.append(String.valueOf(newLocation.getLongitude()));
        } else {
            et.getEditableText().clear();
        }
    }
}
