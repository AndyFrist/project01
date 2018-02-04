package com.hh.gridview_recyclerview.utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;

import com.hh.gridview_recyclerview.myApplication.AndFixApplication;

/**
 * Created by Administrator on 2017/9/20.
 */

public class PhoneLocutionUtils {

    LocationManager lm;
    LocationListener ll = new LocationListener() {
        public void onLocationChanged(Location location) {
            updateLocation.updateView(location);
        }

        @Override
        public void onProviderDisabled(String provider) {
            updateLocation.updateView(null);
        }

        @Override
        public void onProviderEnabled(String provider) {
            if (ContextCompat.checkSelfPermission(AndFixApplication.getTopActivity(), permissions[0]) != PackageManager.PERMISSION_GRANTED) {
                Location l = lm.getLastKnownLocation(provider);
                updateLocation.updateView(l);
            }
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }
    };
    String[] permissions = {Manifest.permission.LOCATION_HARDWARE, Manifest.permission.ACCESS_COARSE_LOCATION};

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

    public void getlocation(UpdateLocationListener updateLocation) {
        this.updateLocation = updateLocation;
        lm = (LocationManager) AndFixApplication.getTopActivity().getSystemService(Context.LOCATION_SERVICE);
        String bestProvider = lm.getBestProvider(getCriteria(), true);
        if (ContextCompat.checkSelfPermission(AndFixApplication.getTopActivity(), permissions[0]) != PackageManager.PERMISSION_GRANTED) {
            Location l = lm.getLastKnownLocation(bestProvider);
            updateLocation.updateView(l);
            lm.requestLocationUpdates(bestProvider, 5000, 8, ll);
        }
    }

    private UpdateLocationListener updateLocation;
    interface UpdateLocationListener{
        void updateView(Location newLocation);
    }
}
