package org.uts.powercoil.konsultan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.uts.powercoil.EditListrik;
import org.uts.powercoil.MainActivity;
import org.uts.powercoil.R;
import org.uts.powercoil.api.ApiClient;
import org.uts.powercoil.api.ApiInterface;
import org.uts.powercoil.api.ApiInterfacePengkajian;
import org.uts.powercoil.model.PostPutDelPengkajian;
import org.uts.powercoil.model.PostPutDelPower;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditPengkajian extends AppCompatActivity {

    EditText editId, editnama, editalamat, editpelaksana, txtPut;
    ApiInterfacePengkajian mApiInterface;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_pengkajian);

        setTitle("Edit Pengkajian");

        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);





        txtPut = (EditText) findViewById(R.id.putExtra);


        editId = (EditText) findViewById(R.id.editId);
        editnama = (EditText) findViewById(R.id.editNama);
        editalamat = (EditText) findViewById(R.id.editAlamat);
        editpelaksana = (EditText) findViewById(R.id.editPelaksana);

        Intent intent = getIntent();
        editId.setText(intent.getStringExtra("id"));
        editnama.setText(intent.getStringExtra("nama"));
        editalamat.setText(intent.getStringExtra("alamat"));
        editpelaksana.setText(intent.getStringExtra("pelaksana"));

        txtPut.setText(intent.getStringExtra("put"));

        String ambil = txtPut.getText().toString();

        mApiInterface = ApiClient.getClient().create(ApiInterfacePengkajian.class);

        btnSubmit = (Button) findViewById(R.id.btn_submit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<PostPutDelPengkajian> updatePengkajianCall = mApiInterface.putPengkajian(
                        editId.getText().toString(),
                        editnama.getText().toString(),
                        editalamat.getText().toString(),
                        editpelaksana.getText().toString());
                updatePengkajianCall.enqueue(new Callback<PostPutDelPengkajian>() {
                    @Override
                    public void onResponse(Call<PostPutDelPengkajian> call, Response<PostPutDelPengkajian> response) {
                        if (ambil.equals("konsultan")) {
                            Intent intent = new Intent(EditPengkajian.this, HomeKonsultan.class);
                            startActivity(intent);
                            finish();
                        }else {
                            Intent intent = new Intent(EditPengkajian.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<PostPutDelPengkajian> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        Button btnDelete = (Button) findViewById(R.id.btn_delete);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editId.getText().toString().trim().isEmpty()==false){
                    Call<PostPutDelPengkajian> deletePengkajian = mApiInterface.deletePengkajian(editId.getText().toString());
                    deletePengkajian.enqueue(new Callback<PostPutDelPengkajian>() {
                        @Override
                        public void onResponse(Call<PostPutDelPengkajian> call, Response<PostPutDelPengkajian> response) {
                            if (ambil.equals("konsultan")) {
                                Intent intent = new Intent(EditPengkajian.this, HomeKonsultan.class);
                                startActivity(intent);
                                finish();
                            }else {
                                Intent intent = new Intent(EditPengkajian.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }

                        @Override
                        public void onFailure(Call<PostPutDelPengkajian> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                        }
                    });
                }else{
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                }
            }
        });
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