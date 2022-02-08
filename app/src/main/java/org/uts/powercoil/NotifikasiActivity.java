package org.uts.powercoil;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.uts.powercoil.adapter.ListrikAdapter;
import org.uts.powercoil.adapter.NotifAdapter;
import org.uts.powercoil.api.Api;
import org.uts.powercoil.fragment.HomeFragment;
import org.uts.powercoil.model.ModelListrik;
import org.uts.powercoil.operator.HomeOperator;

import java.util.ArrayList;
import java.util.List;


public class NotifikasiActivity extends AppCompatActivity implements NotifAdapter.onSelectData {

    RecyclerView rvNotif;
    NotifAdapter notifAdapter;
    ProgressDialog progressDialog;
    List<ModelListrik> modelListrik = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifikasi);

//        ActionBar actionBar = getSupportActionBar();
//        setTitle("List Pembangkit Listrik");
//        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().hide();

        BottomNavigationView bottomNavigation = findViewById(R.id.bot_navigation);

        MenuItem item = bottomNavigation.getMenu().findItem(R.id.nav_pemberitahuan);
        item.setChecked(true);



        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Akan Menampilan data...");

        rvNotif = findViewById(R.id.rvNotif);
        rvNotif.setHasFixedSize(true);
        rvNotif.setLayoutManager(new LinearLayoutManager(this));

        getHotel();


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

                        break;
                    case R.id.navigation_notifications:
                        if (ambil.equals("operator")) {
                            Intent newAct2 = new Intent(getApplicationContext(), MapsActivity.class);
                            newAct2.putExtra("put", "operator");
                            startActivity(newAct2);
                            overridePendingTransition(R.anim.slide_in_right, android.R.anim.slide_out_right);
                        }else {
                            Intent newAct2 = new Intent(getApplicationContext(), MapsActivity.class);
                            startActivity(newAct2);
                            overridePendingTransition(R.anim.slide_in_right, android.R.anim.slide_out_right);
                        }

                }
                return getFragmentPage(fragment);
            }
        });

    }

    private void getHotel() {
        progressDialog.show();
        AndroidNetworking.get(Api.Listrik)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            progressDialog.dismiss();
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
                                showHotel();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(NotifikasiActivity.this,
                                    "Gagal menampilkan data!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        progressDialog.dismiss();
                        Toast.makeText(NotifikasiActivity.this,
                                "Tidak ada jaringan internet!", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void showHotel() {
        notifAdapter = new NotifAdapter(NotifikasiActivity.this, modelListrik, this);
        rvNotif.setAdapter(notifAdapter);
    }

    @Override
    public void onSelected(ModelListrik modelListrik) {
        Intent intent = new Intent(NotifikasiActivity.this, DetailActivity.class);
        intent.putExtra("detailListrik", modelListrik);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
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
