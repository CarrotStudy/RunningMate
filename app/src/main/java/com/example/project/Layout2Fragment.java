package com.example.project;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.skt.Tmap.TMapView;

// bottomNavigation 에서 매칭된 두번째 fragment
public class Layout2Fragment extends Fragment {

    Button btnGps;
    TextView tvGps;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_layout2, container, false);
        Button btnGps = (Button) root.findViewById(R.id.btn_Gps);
        TextView tvGps = (TextView) root.findViewById(R.id.tv_Gps);

        LinearLayout linearLayoutTmap = (LinearLayout) root.findViewById(R.id.linearLayoutTmap);
        TMapView tMapView = new TMapView(root.getContext());


        tMapView.setSKTMapApiKey("l7xxbe621f3bb7e5409bbc8dd77c60a81ca3");
        linearLayoutTmap.addView(tMapView);


        final LocationManager lm = (LocationManager) root.getContext().getSystemService(Context.LOCATION_SERVICE);

        btnGps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= 23 &&
                        ContextCompat.checkSelfPermission(root.getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                            0);
                } else {
                    Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    String provider = location.getProvider();
                    double longitude = location.getLongitude();
                    double latitude = location.getLatitude();
                    double altitude = location.getAltitude();

                    tvGps.setText("위치정보 : " + provider + "\n" +
                            "위도 : " + longitude + "\n" +
                            "경도 : " + latitude + "\n" +
                            "고도  : " + altitude);

               
                }
            }
        });
        return root;

    }
    final LocationListener gpsLocationListener = new LocationListener() {
        public void onLocationChanged(Location location) {

            String provider = location.getProvider();
            double longitude = location.getLongitude();
            double latitude = location.getLatitude();
            double altitude = location.getAltitude();

            tvGps.setText("위치정보 : " + provider + "\n" +
                    "위도 : " + longitude + "\n" +
                    "경도 : " + latitude + "\n" +
                    "고도  : " + altitude);

        }

        public void onStatusChanged(String provider, int status, Bundle extras) {
        }

        public void onProviderEnabled(String provider) {
        }

        public void onProviderDisabled(String provider) {
        }
    };


}



