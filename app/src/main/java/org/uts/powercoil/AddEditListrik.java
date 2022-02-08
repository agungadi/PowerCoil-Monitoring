package org.uts.powercoil;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import org.uts.powercoil.api.PostListrik;
import org.uts.powercoil.user.UserActivity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class AddEditListrik extends AppCompatActivity {

    EditText tNama, tKapasitas, tTerisi, tArus, tRadiasi, tDeskripsi, tJumlah, tKordinat;
    private ImageView imageView, imageViewGone;
    private Button mSubmitBtn, photoButton;

    private static final int IMAGE_REQUEST = 1;
    String currentImagePath = null;
    private Bitmap bitmap;

    TextView mtextLat, mtextLong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_listrik);

        setTitle("Tambah Pembangkit Listrik");

        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mtextLat = findViewById(R.id.textLat);
        mtextLong = findViewById(R.id.textLong);


        tNama = (EditText) findViewById(R.id.textName);
        tKapasitas = (EditText) findViewById(R.id.textKapasitas);
        tTerisi = (EditText) findViewById(R.id.textTerisi);
        tArus = (EditText) findViewById(R.id.textArus);
        tRadiasi = (EditText) findViewById(R.id.textRadiasi);
        tDeskripsi = (EditText) findViewById(R.id.textDeskripsi);
        tJumlah = (EditText) findViewById(R.id.textJumlah);
        tKordinat = (EditText) findViewById(R.id.textKordinat);

        tKordinat.setKeyListener(null);

        imageView = (ImageView) findViewById(R.id.imageView1);
        imageViewGone = (ImageView) findViewById(R.id.imageViewgone);


        mSubmitBtn = (Button) findViewById(R.id.submitBtn);
        photoButton = (Button) findViewById(R.id.button1);

        mSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitReport();
            }
        });

        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        LocationListener ll = new mylocationlistener();
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10, 5, ll);


    }

    public void captureImage(View view) {

        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (cameraIntent.resolveActivity(getPackageManager()) != null) {
            File imageFile = null;
            try {
                imageFile = getImageFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (imageFile != null) {
                Uri imageUri = FileProvider.getUriForFile(this, "org.uts.powercoil.fileprovider", imageFile);
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                //cameraIntent.putExtra("image_path",currentImagePath);
                startActivityForResult(cameraIntent, IMAGE_REQUEST);

            }
        }

    }


    private File getImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyymmdd_HHmmss").format(new Date());
        String imageName = "jpg_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        File imageFile = File.createTempFile(imageName, ".jpg", storageDir);
        currentImagePath = imageFile.getAbsolutePath();
        return imageFile;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //if (requestCode == IMAGE_REQUEST && resultCode == RESULT_OK && data!=null) {
        //  Uri imageUri = data.getData();
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            File imgFile = new File(currentImagePath);
            if (imgFile.exists()) {
                bitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                imageView.setImageBitmap(bitmap);
                imageViewGone.setVisibility(View.GONE);
                imageView.setVisibility(View.VISIBLE);
            }

        }
    }

    private String imageToString() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] imgByte = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imgByte, Base64.DEFAULT);

    }

    public void submitReport() {
        String nama = tNama.getText().toString();
        String kapasitas = tKapasitas.getText().toString();
        String terisi = tTerisi.getText().toString();
        String arus = tArus.getText().toString();
        String image = imageToString();
        String radiasi = tRadiasi.getText().toString();
        String deskripsi = tDeskripsi.getText().toString();
        String jumlah = tJumlah.getText().toString();
        String kordinat = tKordinat.getText().toString();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(PostListrik.LISTRIKURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        PostListrik api = retrofit.create(PostListrik.class);


        Call<String> call = api.getlISTRIK(nama, kapasitas, terisi, arus, image, radiasi, deskripsi, jumlah, kordinat);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.i("Responsestring", response.body().toString());

                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.i("onSuccess", response.body().toString());
                        Toast.makeText(AddEditListrik.this, "Terkirim", Toast.LENGTH_LONG).show();
//                        Intent intent = new Intent(AddEditListrik.this, MainActivity.class);
//                        startActivity(intent);
//                        finish();
                        Intent intent = new Intent(AddEditListrik.this, ListrikActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                    } else {
                        Log.i("onEmptyResponse", "Returned empty response");
                        Toast.makeText(AddEditListrik.this, "Nothing returned", Toast.LENGTH_LONG).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });

    }


    class mylocationlistener implements LocationListener {

        @Override
        public void onLocationChanged(Location location) {

            if (location != null) {
                double pLong = location.getLongitude();
                double pLat = location.getLatitude();

                String Long = Double.toString(pLong);
                String Lat =  Double.toString(pLat);

                tKordinat.setText(Lat +", "+ Long);
                mtextLat.setText(Double.toString(pLat));
                mtextLong.setText(Double.toString(pLong));

            }
        }


    }
}