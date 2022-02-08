package org.uts.powercoil.fragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.uts.powercoil.LoginActivity;
import org.uts.powercoil.R;

public class HomeFragment extends Fragment {
    private GoogleMap mMap;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.frg);  //use SuppoprtMapFragment for using in fragment instead of activity  MapFragment = activity   SupportMapFragment = fragment
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mMap = googleMap;
                CameraPosition googlePlex = CameraPosition.builder()
                        .target(new LatLng(-7.152479,111.886929))
                        .zoom(12)
                        .bearing(0)
                        .tilt(45)
                        .build();
                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(googlePlex), 10000, null);

                mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(-7.2369247,111.8949562))
                        .title("PLTS1")
                        .icon(bitmapDescriptorFromVector(getActivity(),R.drawable.maphijau)));

                mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(-7.1890568,111.89927))
                        .title("PLTS2")
                        .icon(bitmapDescriptorFromVector(getActivity(),R.drawable.maphijau)));


                mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(-7.1310719,111.8903931))
                        .title("PLTS3")
                        .icon(bitmapDescriptorFromVector(getActivity(),R.drawable.mapmerah)));
                mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(-7.1970194,111.8666287))
                        .title("PLTS4")
                        .icon(bitmapDescriptorFromVector(getActivity(),R.drawable.mapkuning)));

                mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(
                                -7.1168661,111.8523328))
                        .title("PLTB1")
                        .icon(bitmapDescriptorFromVector(getActivity(),R.drawable.maphijau)));

                mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(
                                -7.0866799,111.8914612
                        ))
                        .title("PLTB2")
                        .icon(bitmapDescriptorFromVector(getActivity(),R.drawable.mapmerah)));

                mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(
                                -7.1061799,111.9174692))
                        .title("PLTB3")
                        .icon(bitmapDescriptorFromVector(getActivity(),R.drawable.mapkuning)));
                mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(
                                -7.0812897,111.8435706))
                        .title("PLTB4")
                        .icon(bitmapDescriptorFromVector(getActivity(),R.drawable.mapkuning)));

                // Add a marker in Sydney and move the camera
//                LatLng sydney = new LatLng(-34, 151);
//                mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//                mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

            }
        });



        return view;

    }



    private BitmapDescriptor bitmapDescriptorFromVector(Context context, int vectorResId) {
        Drawable vectorDrawable = ContextCompat.getDrawable(context, vectorResId);
        vectorDrawable.setBounds(0, 0, vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight());
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }
}
