package org.uts.powercoil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.uts.powercoil.api.ApiClient;
import org.uts.powercoil.api.ApiInterface;
import org.uts.powercoil.model.PostPutDelPower;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditListrik extends AppCompatActivity {

    EditText editId, editKapasitas, editTerisi, editTenaga, editJumlah;
    ApiInterface mApiInterface;
    Button btnSimpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_listrik);

        setTitle("Edit Pembangkit Listrik");

        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        editId = (EditText) findViewById(R.id.textId);
        editKapasitas = (EditText) findViewById(R.id.textKapasitas);
        editTerisi = (EditText) findViewById(R.id.textTerisi);
        editTenaga = (EditText) findViewById(R.id.textTenaga);
        editJumlah = (EditText) findViewById(R.id.textJumlah);

        Intent intent = getIntent();
        editId.setText(intent.getStringExtra("id"));
        editKapasitas.setText(intent.getStringExtra("kapasitas"));
        editTerisi.setText(intent.getStringExtra("terisi"));
        editTenaga.setText(intent.getStringExtra("radiasi_matahari"));
        editJumlah.setText(intent.getStringExtra("jumlah"));

        mApiInterface = ApiClient.getClient().create(ApiInterface.class);

        btnSimpan = (Button) findViewById(R.id.submitBtn);

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<PostPutDelPower> updateKontakCall = mApiInterface.putKontak(
                        editId.getText().toString(),
                        editKapasitas.getText().toString(),
                        editTerisi.getText().toString(),
                        editTenaga.getText().toString(),
                        editJumlah.getText().toString());
                updateKontakCall.enqueue(new Callback<PostPutDelPower>() {
                    @Override
                    public void onResponse(Call<PostPutDelPower> call, Response<PostPutDelPower> response) {
                        Intent intent = new Intent(EditListrik.this, ListrikActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onFailure(Call<PostPutDelPower> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        Button btnDelete = (Button) findViewById(R.id.btn_del);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editId.getText().toString().trim().isEmpty()==false){
                    Call<PostPutDelPower> deleteKontak = mApiInterface.deleteKontak(editId.getText().toString());
                    deleteKontak.enqueue(new Callback<PostPutDelPower>() {
                        @Override
                        public void onResponse(Call<PostPutDelPower> call, Response<PostPutDelPower> response) {
                            Intent intent = new Intent(EditListrik.this, ListrikActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            finish();
                        }

                        @Override
                        public void onFailure(Call<PostPutDelPower> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "Wait", Toast.LENGTH_LONG).show();
                        }
                    });
                }else{
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}