package org.uts.powercoil.konsultan;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;

import org.uts.powercoil.DetailActivity;
import org.uts.powercoil.EditListrik;
import org.uts.powercoil.R;
import org.uts.powercoil.api.ApiInterface;
import org.uts.powercoil.model.ModelListrik;
import org.uts.powercoil.model.Pengkajian;

public class DetailPengkajian extends AppCompatActivity {


    TextView txId, txtNama, txtAlamat, txtPelaksana, txtTanggal;
    String Id, Nama, Alamat, Pelaksana, Tanggal, PDF;
    Pengkajian pengkajian;
    ApiInterface mApiInterface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pengkajian);


        ActionBar actionBar = getSupportActionBar();
        setTitle("Detail Pengkajian");
        actionBar.setDisplayHomeAsUpEnabled(true);


        EditText txtPut = (EditText) findViewById(R.id.putExtra);


        Intent intent = getIntent();
        txtPut.setText(intent.getStringExtra("put"));

        String ambil = txtPut.getText().toString();



        pengkajian = (Pengkajian) getIntent().getSerializableExtra("detailPengkajian");
        if (pengkajian != null) {

//            get String
            Id = pengkajian.getId();
            Nama = pengkajian.getNama();
            Alamat = pengkajian.getAlamat();
            Pelaksana = pengkajian.getPelaksana();
            Tanggal = pengkajian.getTanggal();
            PDF = pengkajian.getPdf();
            //set Id
            txId = findViewById(R.id.id);

            txtNama = findViewById(R.id.name);
            txtAlamat = findViewById(R.id.alamat);
            txtPelaksana = findViewById(R.id.pelaksana);
            txtTanggal = findViewById(R.id.tanggal);

            //show String to Text
            txId.setText(Id);
            txtNama.setText(Nama);
            txtAlamat.setText(Alamat);
            txtPelaksana.setText(Pelaksana);
            txtTanggal.setText(Tanggal);


            Button btnedit = (Button) findViewById(R.id.btn_edit);

            if(ambil.equals("lapangan")){
                btnedit.setVisibility(View.INVISIBLE);
            }

            btnedit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(ambil.equals("konsultan")) {
                        Intent intent = new Intent(DetailPengkajian.this, EditPengkajian.class);
                        intent.putExtra("put", "konsultan");
                        intent.putExtra("id", Id);
                        intent.putExtra("nama", Nama);
                        intent.putExtra("alamat", Alamat);
                        intent.putExtra("pelaksana", Pelaksana);
                        startActivity(intent);
                    }else {
                        Intent intent = new Intent(DetailPengkajian.this, EditPengkajian.class);
                        intent.putExtra("id", Id);
                        intent.putExtra("nama", Nama);
                        intent.putExtra("alamat", Alamat);
                        intent.putExtra("pelaksana", Pelaksana);
                        startActivity(intent);
                    }

                }
            });


            Button btndown = (Button) findViewById(R.id.down_spk);
            btndown.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(PDF));
                    startActivity(browserIntent);
                }
            });

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