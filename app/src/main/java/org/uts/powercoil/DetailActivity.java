package org.uts.powercoil;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.uts.powercoil.api.ApiInterface;
import org.uts.powercoil.model.ModelListrik;
import org.uts.powercoil.model.PostPutDelPower;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity implements OnMapReadyCallback {


    GoogleMap googleMaps;
    TextView txtId, txtName, txtKapasitas, txtTerisi,txtArus, txtTenaga, txtDeskripsi, txtJumlah;
    String Id, Name, Kapasitas, Terisi,Arus, Tenaga, Deskripsi, Jumlah;
    ModelListrik modelListrik;
    ApiInterface mApiInterface;
    EditText edtId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        setTitle("Detail Pembangkit Listrik");

        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        Button btnEdit = (Button) findViewById(R.id.btn_edit);
//
//        btnEdit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent intent = new Intent(DetailActivity.this, AddEditListrik.class);
//                startActivity(intent);            }
//        });
//



        //show maps
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        modelListrik = (ModelListrik) getIntent().getSerializableExtra("detailListrik");
        if (modelListrik != null) {

//            get String
            Id = modelListrik.getTxtId();
            Name = modelListrik.getTxtNama();
            Kapasitas = modelListrik.getTxtKapasitas();
            Terisi = modelListrik.getTxtTerisi();
            Arus = modelListrik.getTxtArus();
            Tenaga = modelListrik.getTxtRadiasi();
            Deskripsi = modelListrik.getTxtDeskripsi();
            Jumlah = modelListrik.getTxtJumlah();

            Tenaga = modelListrik.getTxtRadiasi();
            //set Id
            txtId = findViewById(R.id.tvId);

            txtTenaga =findViewById(R.id.tvTenaga);
            txtName = findViewById(R.id.tvNama);
            txtKapasitas = findViewById(R.id.tvKapasitas);
            txtTerisi = findViewById(R.id.tvTerisi);
            txtArus = findViewById(R.id.tvArus);
            txtTenaga = findViewById(R.id.tvTenaga);
            txtDeskripsi = findViewById(R.id.tvDeskripsi);
            txtJumlah = findViewById(R.id.tvJumlah);
            //show String to Text
            txtId.setText(Id);
            txtName.setText(Name);
            txtKapasitas.setText(Kapasitas);
            txtTerisi.setText(Terisi);
            txtArus.setText(Arus);
            txtTenaga.setText(Tenaga);
            txtDeskripsi.setText(Deskripsi);
            txtJumlah.setText(Jumlah);
            TextView satuan = (TextView) findViewById(R.id.tvSatuan);




            Button btnedit = (Button) findViewById(R.id.btn_edit);

            btnedit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(DetailActivity.this, EditListrik.class);
                    intent.putExtra("id", Id);
                    intent.putExtra("kapasitas", Kapasitas);
                    intent.putExtra("terisi", Terisi);
                    intent.putExtra("radiasi_matahari", Tenaga);
                    intent.putExtra("jumlah", Jumlah);
                    startActivity(intent);

                }
            });






            if(modelListrik.getTxtDeskripsi().equals("Pembangkit Listrik Tenaga Surya")){
                satuan.setText("m2");
            }else{
                satuan.setText("m/s");
            }

            TextView satuanJumlah = (TextView) findViewById(R.id.tvSatuanJml);
            if(modelListrik.getTxtDeskripsi().equals("Pembangkit Listrik Tenaga Surya")){
                satuanJumlah.setText(" Panel Surya");
            }else{
                satuanJumlah.setText(" Turbin");
            }

            ImageView levelsatu = (ImageView) findViewById(R.id.lvl1);
            ImageView leveldua = (ImageView) findViewById(R.id.lvl2);
            ImageView leveltiga = (ImageView) findViewById(R.id.lvl3);
            ImageView levelempat = (ImageView) findViewById(R.id.lvl4);
            String angin = modelListrik.getTxtRadiasi();
            String matahari = modelListrik.getTxtRadiasi();

            Double angin1 = Double.parseDouble(angin);
            Double matahari1 = Double.parseDouble(matahari);

            if(modelListrik.getTxtDeskripsi().equals("Pembangkit Listrik Tenaga Surya")){
                if (matahari1 < 1){
                    levelsatu.setVisibility(View.VISIBLE);
                }else if(matahari1 >= 2 && matahari1 <= 3.7 ){
                    leveldua.setVisibility(View.VISIBLE);
                }else if(matahari1 >= 3.8 && matahari1 <= 4.8 ){
                    leveltiga.setVisibility(View.VISIBLE);
                }else if(matahari1 > 4.8) {
                    levelempat.setVisibility(View.VISIBLE);
                }
            }else{
                if (angin1 < 4){
                    levelsatu.setVisibility(View.VISIBLE);
                }else if(angin1 >= 6 && matahari1 <= 7 ){
                    leveldua.setVisibility(View.VISIBLE);
                }else if(angin1 >= 8 && matahari1 <= 9 ){
                    leveltiga.setVisibility(View.VISIBLE);
                }else if(angin1 >= 10) {
                    levelempat.setVisibility(View.VISIBLE);
                }
            }
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

    @Override
    public void onMapReady(GoogleMap googleMap) {

        //get LatLong
        String[] latlong =  modelListrik.getKoordinat().split(",");
        double latitude = Double.parseDouble(latlong[0]);
        double longitude = Double.parseDouble(latlong[1]);

        googleMaps = googleMap;
        LatLng latLng = new LatLng(latitude, longitude);
        googleMaps.addMarker(new MarkerOptions().position(latLng).title(Name));
        googleMaps.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        googleMaps.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16));
        googleMaps.getUiSettings().setAllGesturesEnabled(true);
        googleMaps.getUiSettings().setZoomGesturesEnabled(true);
        googleMaps.setTrafficEnabled(true);
    }
}