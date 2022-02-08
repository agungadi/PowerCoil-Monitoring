package org.uts.powercoil.konsultan;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.uts.powercoil.AddEditListrik;
import org.uts.powercoil.DetailActivity;
import org.uts.powercoil.ListrikActivity;
import org.uts.powercoil.R;
import org.uts.powercoil.adapter.ListrikAdapter;
import org.uts.powercoil.adapter.PengkajianAdapter;
import org.uts.powercoil.api.Api;
import org.uts.powercoil.api.ApiPengkajian;
import org.uts.powercoil.model.ModelListrik;
import org.uts.powercoil.model.Pengkajian;

import java.util.ArrayList;
import java.util.List;

public class PengkajianActivity extends AppCompatActivity implements PengkajianAdapter.onSelectData {

    RecyclerView rvPengkajian;
    PengkajianAdapter pengkajianAdapter;
    ProgressDialog progressDialog;
    List<Pengkajian> pengkajian = new ArrayList<>();
    EditText txtPut;
    String ambil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengkajian);

        ActionBar actionBar = getSupportActionBar();
        setTitle("List Pengkajian");
        actionBar.setDisplayHomeAsUpEnabled(true);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Akan Menampilan data...");

        rvPengkajian = findViewById(R.id.rvPengkajian);
        rvPengkajian.setHasFixedSize(true);
        rvPengkajian.setLayoutManager(new LinearLayoutManager(this));

        txtPut = (EditText) findViewById(R.id.putExtra);


        Intent intent = getIntent();
        txtPut.setText(intent.getStringExtra("put"));

        ambil = txtPut.getText().toString();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabPengkajian);

        if (ambil.equals("lapangan")) {
            fab.setVisibility(View.INVISIBLE);
        }
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (ambil.equals("konsultan")) {
                        Intent intent = new Intent(PengkajianActivity.this, AddEditPengkajian.class);
                        intent.putExtra("put", "konsultan");
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(PengkajianActivity.this, AddEditPengkajian.class);
                        startActivity(intent);
                    }
                }
            });

        getHotel();
    }

    private void getHotel() {
        progressDialog.show();
        AndroidNetworking.get(ApiPengkajian.Pengkajian)
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
                                Pengkajian dataApi = new Pengkajian();
                                dataApi.setId(temp.getString("id"));
                                dataApi.setNama(temp.getString("Nama"));
                                dataApi.setAlamat(temp.getString("Alamat"));
                                dataApi.setPelaksana(temp.getString("Pelaksana"));
                                dataApi.setPdf(temp.getString("pathToFile"));
                                dataApi.setTanggal(temp.getString("tanggal"));
                                pengkajian.add(dataApi);
                                showHotel();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(PengkajianActivity.this,
                                    "Gagal menampilkan data!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        progressDialog.dismiss();
                        Toast.makeText(PengkajianActivity.this,
                                "Tidak ada jaringan internet!", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void showHotel() {
        pengkajianAdapter = new PengkajianAdapter(PengkajianActivity.this, pengkajian, this);
        rvPengkajian.setAdapter(pengkajianAdapter);
    }

    @Override
    public void onSelected(Pengkajian pengkajian) {
        if (ambil.equals("konsultan")) {
            Intent intent = new Intent(PengkajianActivity.this, DetailPengkajian.class);
            intent.putExtra("put", "konsultan");
            intent.putExtra("detailPengkajian", pengkajian);
            startActivity(intent);
        }else if(ambil.equals("lapangan")) {
            Intent intent = new Intent(PengkajianActivity.this, DetailPengkajian.class);
            intent.putExtra("put", "lapangan");
            intent.putExtra("detailPengkajian", pengkajian);
            startActivity(intent);
        }else{
            Intent intent = new Intent(PengkajianActivity.this, DetailPengkajian.class);
            intent.putExtra("detailPengkajian", pengkajian);
            startActivity(intent);
            }
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