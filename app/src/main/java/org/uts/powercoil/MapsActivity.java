package org.uts.powercoil;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.uts.powercoil.api.Api;
import org.uts.powercoil.databinding.ActivityMapsBinding;
import org.uts.powercoil.fragment.HomeFragment;
import org.uts.powercoil.model.ModelListrik;
import org.uts.powercoil.operator.HomeOperator;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    ProgressDialog progressDialog;

    private GoogleMap mMap;

    List<ModelListrik> modelListrik = new ArrayList<>();

    private ActivityMapsBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView bottomNavigation = findViewById(R.id.bot_navigation);

        MenuItem item = bottomNavigation.getMenu().findItem(R.id.navigation_notifications);
        item.setChecked(true);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        EditText txtPut = (EditText) findViewById(R.id.putExtra);

        Intent intent = getIntent();
        txtPut.setText(intent.getStringExtra("put"));
        String ambil = txtPut.getText().toString();


        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {


            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Fragment fragment = null;
                AppCompatActivity app = null;
                //Menantukan halaman Fragment yang akan tampil
                switch (item.getItemId()){
                    case R.id.navigation_home:
//                        fragment = new NotificationFragment();
                        if (ambil.equals("operator")) {
                            Intent newAct = new Intent(getApplicationContext(), HomeOperator.class);
                            startActivity(newAct);
                            overridePendingTransition(R.anim.slide_in_left, android.R.anim.slide_out_right);
                        }else {
                            Intent newAct = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(newAct);
                            overridePendingTransition(R.anim.slide_in_left, android.R.anim.slide_out_right);
                        }
                        break;
                    case R.id.nav_pemberitahuan:
                        if (ambil.equals("operator")) {
                            Intent newAct1 = new Intent(getApplicationContext(), NotifikasiActivity.class);
                            newAct1.putExtra("put", "operator");
                            startActivity(newAct1);
                            overridePendingTransition(R.anim.slide_in_left, android.R.anim.slide_out_right);
                        }else{
                            Intent newAct1 = new Intent(getApplicationContext(), NotifikasiActivity.class);
                            startActivity(newAct1);
                            overridePendingTransition(R.anim.slide_in_left, android.R.anim.slide_out_right);
                        }
                        break;
                    case R.id.navigation_notifications:

                        break;

                }
                return getFragmentPage(fragment);
            }
        });

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        getAllDataLocationLatLng();
//        String[] latlong =  modelListrik.getKoordinat().split(",");
//        double latitude = Double.parseDouble(latlong[0]);
//        double longitude = Double.parseDouble(latlong[1]);
//
//        LatLng latLng = new LatLng(latitude, longitude);
//
//        arrayList.add(latLng);
//
//        // Add a marker in Sydney and move the camera
//        for (int i=0; i<arrayList.size();i++){
//            mMap.addMarker(new MarkerOptions().position(arrayList.get(i)).title("Marker"));
//            mMap.animateCamera(CameraUpdateFactory.zoomBy(15.0f));
//            mMap.moveCamera(CameraUpdateFactory.newLatLng(arrayList.get(i)));
//        }

    }




    private void getAllDataLocationLatLng() {
        AndroidNetworking.get(Api.Listrik)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray playerArray = response.getJSONArray("result");
                            for (int i = 0; i < playerArray.length(); i++) {
                                JSONObject temp = playerArray.getJSONObject(i);
                                ModelListrik dataApi = new ModelListrik();
                                dataApi.setTxtId(temp.getString("id"));
                                dataApi.setTxtNama(temp.getString("nama"));
                                dataApi.setTxtKapasitas(temp.getString("kapasitas"));
                                dataApi.setTxtTerisi(temp.getString("terisi"));
                                dataApi.setTxtArus(temp.getString("arus_dc"));
                                dataApi.setGambar(temp.getString("gambar_url"));
                                dataApi.setTxtRadiasi(temp.getString("radiasi_matahari"));
                                dataApi.setTxtDeskripsi(temp.getString("deskripsi"));
                                dataApi.setTxtJumlah(temp.getString("jumlah_panel"));
                                dataApi.setKoordinat(temp.getString("kordinat"));
                                modelListrik.add(dataApi);
                                initMarker(modelListrik);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(MapsActivity.this,
                                    "Gagal menampilkan data!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(MapsActivity.this,
                                "Tidak ada jaringan internet!", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void initMarker(List<ModelListrik> listData){
        //iterasi semua data dan tampilkan markernya
//        //get LatLong
//        String[] latlong =  modelListrik.getKoordinat().split(",");
//        double latitude = Double.parseDouble(latlong[0]);
//        double longitude = Double.parseDouble(latlong[1]);
        for (int i=0; i<modelListrik.size(); i++){
            //set latlng nya
            String[] latlong =  modelListrik.get(i).getKoordinat().split(",");
            double latitude = Double.parseDouble(latlong[0]);
            double longitude = Double.parseDouble(latlong[1]);
            LatLng location = new LatLng(latitude, longitude);
            //tambahkan markernya

            Integer vol = Integer.parseInt(modelListrik.get(i).getTxtTerisi());
            Integer volterisi = Integer.parseInt(modelListrik.get(i).getTxtKapasitas());

            String angin = modelListrik.get(i).getTxtRadiasi();
            String matahari = modelListrik.get(i).getTxtRadiasi();

            Double angin1 = Double.parseDouble(angin);
            Double matahari1 = Double.parseDouble(matahari);


            if(modelListrik.get(i).getTxtDeskripsi().equals("Pembangkit Listrik Tenaga Bayu")){
                if (vol == volterisi && angin1 > 7) {
                    mMap.addMarker(new MarkerOptions().position(location).title(modelListrik.get(i).getTxtNama() + "- Full Capacity & Angin Terlalu Kencang").icon(BitmapDescriptorFactory.fromResource(R.drawable.mapmerah)));
                }else if (vol == volterisi){
                    mMap.addMarker(new MarkerOptions().position(location).title(modelListrik.get(i).getTxtNama() + "- Full Capacity").icon(BitmapDescriptorFactory.fromResource(R.drawable.mapkuning)));
                }else if (angin1 > 7) {
                    mMap.addMarker(new MarkerOptions().position(location).title(modelListrik.get(i).getTxtNama() + "- Angin Terlalu Kencang").icon(BitmapDescriptorFactory.fromResource(R.drawable.mapkuning)));
                }else{
                    mMap.addMarker(new MarkerOptions().position(location).title(modelListrik.get(i).getTxtNama()).icon(BitmapDescriptorFactory.fromResource(R.drawable.maphijau)));
                }

            }else{
                if (vol == volterisi && matahari1 > 4.8) {
                    mMap.addMarker(new MarkerOptions().position(location).title(modelListrik.get(i).getTxtNama() + "- Full Capacity & Radiasi Matahari Tinggi").icon(BitmapDescriptorFactory.fromResource(R.drawable.mapmerah)));
                }else if (vol == volterisi){
                    mMap.addMarker(new MarkerOptions().position(location).title(modelListrik.get(i).getTxtNama() + "- Full Capacity").icon(BitmapDescriptorFactory.fromResource(R.drawable.mapkuning)));
                }else if (matahari1 > 4.8) {
                    mMap.addMarker(new MarkerOptions().position(location).title(modelListrik.get(i).getTxtNama() + "- Radiasi Matahari Tinggi").icon(BitmapDescriptorFactory.fromResource(R.drawable.mapkuning)));
                }
                else{
                    mMap.addMarker(new MarkerOptions().position(location).title(modelListrik.get(i).getTxtNama() + "- Normal").icon(BitmapDescriptorFactory.fromResource(R.drawable.maphijau)));
                }
            }


            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), 11.0f));
//            mMap.animateCamera(CameraUpdateFactory.zoomBy(11.0f));
        }
    }

    //Menampilkan halaman Fragment
    private boolean getFragmentPage(Fragment fragment){
        if (fragment != null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, fragment)
                    .commit();
            return true;
        }
        return false;
    }


}


