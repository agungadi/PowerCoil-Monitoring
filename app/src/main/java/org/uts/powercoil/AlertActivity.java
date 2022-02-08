package org.uts.powercoil;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.uts.powercoil.adapter.AlertAdapter;
import org.uts.powercoil.adapter.ListrikAdapter;
import org.uts.powercoil.adapter.NotifAdapter;
import org.uts.powercoil.api.Api;
import org.uts.powercoil.model.ModelListrik;

import java.util.ArrayList;
import java.util.List;


public class AlertActivity extends AppCompatActivity implements AlertAdapter.onSelectData {

    RecyclerView rvAlert;
    AlertAdapter alertAdapter;
    ProgressDialog progressDialog;
    List<ModelListrik> modelListrik = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert);

        ActionBar actionBar = getSupportActionBar();
        setTitle("Alert");
        actionBar.setDisplayHomeAsUpEnabled(true);



//        getSupportActionBar().hide();

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Akan Menampilan data...");

        rvAlert = findViewById(R.id.rvAlert);
        rvAlert.setHasFixedSize(true);
        rvAlert.setLayoutManager(new LinearLayoutManager(this));

        getHotel();
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
                            Toast.makeText(AlertActivity.this,
                                    "Gagal menampilkan data!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        progressDialog.dismiss();
                        Toast.makeText(AlertActivity.this,
                                "Tidak ada jaringan internet!", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void showHotel() {
        alertAdapter = new AlertAdapter(AlertActivity.this, modelListrik, this);
        rvAlert.setAdapter(alertAdapter);
    }

    @Override
    public void onSelected(ModelListrik modelListrik) {
        Intent intent = new Intent(AlertActivity.this, DetailActivity.class);
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
}
