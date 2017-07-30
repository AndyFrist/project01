package com.hh.gridview_recyclerview.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.hh.gridview_recyclerview.R;


public class MyMapFragment extends Fragment {
    private FrameLayout map_fl;
    private MapView mapView;
    private GoogleMap googleMap;
    private Context context;
    private Button maptype_btn;
    int i = 0;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        map_fl = (FrameLayout) view.findViewById(R.id.map_fl);
        mapView = new MapView(context);
//        mapView.setLayoutParams(new FrameLayout.LayoutParams(1,1));
        map_fl.addView(mapView);
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap map) {
                googleMap = map;
            }
        });
        mapView.onCreate(savedInstanceState);
        maptype_btn = (Button) view.findViewById(R.id.maptype_btn);
        maptype_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i = i % 5;
                switch (i) {
                    case 0:
                        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                        break;
                    case 1:
                        googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                        break;
                    case 2:
                        googleMap.setMapType(GoogleMap.MAP_TYPE_NONE);
                        break;
                    case 3:
                        googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                        break;
                    case 4:
                        googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                        break;
                }
                i++;
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }
}
