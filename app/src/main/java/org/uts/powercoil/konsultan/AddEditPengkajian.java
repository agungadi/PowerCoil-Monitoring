package org.uts.powercoil.konsultan;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.uts.powercoil.AddEditListrik;
import org.uts.powercoil.MainActivity;
import org.uts.powercoil.R;
import org.uts.powercoil.api.PengkajianInterface;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class AddEditPengkajian extends AppCompatActivity {

    private Button btn_submit;
    public EditText enama, ealamat, epelaksana, txtPut;
    private static final int BUFFER_SIZE = 1024 * 2;
    private String url = "";
    private static final String IMAGE_DIRECTORY = "/demonuts_upload_gallery";

    ActivityResultLauncher<String> mGetContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_pengkajian);


        ActionBar actionBar = getSupportActionBar();
        setTitle("Tambah Pengkajian");
        actionBar.setDisplayHomeAsUpEnabled(true);

        requestMultiplePermissions();
        btn_submit = findViewById(R.id.btn_submit);
        enama = findViewById(R.id.txt_name);
        ealamat = findViewById(R.id.txt_alamat);
        epelaksana = findViewById(R.id.txt_pelaksana);

        txtPut = (EditText) findViewById(R.id.putExtra);


        Intent intent = getIntent();
        txtPut.setText(intent.getStringExtra("put"));


        mGetContent=registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri result) {
                String path = getFilePathFromURI(AddEditPengkajian.this,result);
                Log.d("ioooossss",path);
                uploadVideo(path);

            }
        });


        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGetContent.launch("application/pdf");

            }
        });

    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (resultCode == RESULT_OK) {
//            // Get the Uri of the selected file
//            Uri uri = data.getData();
//
//            String path = getFilePathFromURI(AddEditPengkajian.this,uri);
//            Log.d("ioooossss",path);
//            uploadVideo(path);
//        }
//
//        super.onActivityResult(requestCode, resultCode, data);
//
//    }

    private void uploadVideo(String path){

        String pdfname = String.valueOf(Calendar.getInstance().getTimeInMillis());
        String nama = enama.getText().toString();
        String alamat = ealamat.getText().toString();
        String pelaksana = epelaksana.getText().toString();

        String ambil = txtPut.getText().toString();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(PengkajianInterface.PDFURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        //Create a file object using file path
        File file = new File(path);
        // Parsing any Media type file
        RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), file);
        MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("filename", file.getName(), requestBody);
        RequestBody filename = RequestBody.create(MediaType.parse("text/plain"), pdfname);

        PengkajianInterface getResponse = retrofit.create(PengkajianInterface.class);
        Call<String> call = getResponse.uploadImage(fileToUpload, filename, nama, alamat, pelaksana);
        Log.d("assss","asss");
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.d("mullllll", response.body().toString());
//                Intent intent = new Intent(AddEditPengkajian.this, MainActivity.class);
                if (ambil.equals("konsultan")) {
                    Intent intent = new Intent(AddEditPengkajian.this, HomeKonsultan.class);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(AddEditPengkajian.this, MainActivity.class);
                    startActivity(intent);
                }
                try {
                    JSONObject jsonObject = new JSONObject(response.body().toString());
                    Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_SHORT).show();

                    jsonObject.toString().replace("\\\\","");

                    if (jsonObject.getString("status").equals("true")) {

                        JSONArray dataArray = jsonObject.getJSONArray("data");

                        for (int i = 0; i < dataArray.length(); i++) {
                            JSONObject dataobj = dataArray.getJSONObject(i);
                            url = dataobj.optString("pathToFile");

                        }

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.d("gttt", call.toString());
            }
        });

    }


    public static String getFilePathFromURI(Context context, Uri contentUri) {
        //copy file and send new file path

        File wallpaperDirectory = new File(
                Environment.getExternalStorageDirectory() + IMAGE_DIRECTORY);
        // have the object build the directory structure, if needed.
        if (!wallpaperDirectory.exists()) {
            wallpaperDirectory.mkdirs();
        }

        File copyFile = new File(wallpaperDirectory + File.separator + Calendar.getInstance()
                .getTimeInMillis()+".pdf");
        // create folder if not exists

        copy(context, contentUri, copyFile);
        Log.d("vPath--->",copyFile.getAbsolutePath());

        return copyFile.getAbsolutePath();

    }

    public static void copy(Context context, Uri srcUri, File dstFile) {
        try {
            InputStream inputStream = context.getContentResolver().openInputStream(srcUri);
            if (inputStream == null) return;
            OutputStream outputStream = new FileOutputStream(dstFile);
            copystream(inputStream, outputStream);
            inputStream.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int copystream(InputStream input, OutputStream output) throws Exception, IOException {
        byte[] buffer = new byte[BUFFER_SIZE];

        BufferedInputStream in = new BufferedInputStream(input, BUFFER_SIZE);
        BufferedOutputStream out = new BufferedOutputStream(output, BUFFER_SIZE);
        int count = 0, n = 0;
        try {
            while ((n = in.read(buffer, 0, BUFFER_SIZE)) != -1) {
                out.write(buffer, 0, n);
                count += n;
            }
            out.flush();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                Log.e(e.getMessage(), String.valueOf(e));
            }
            try {
                in.close();
            } catch (IOException e) {
                Log.e(e.getMessage(), String.valueOf(e));
            }
        }
        return count;
    }

    private void  requestMultiplePermissions(){
        Dexter.withActivity(this)
                .withPermissions(

                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        // check if all permissions are granted
                        if (report.areAllPermissionsGranted()) {
                            Toast.makeText(getApplicationContext(), "All permissions are granted by user!", Toast.LENGTH_SHORT).show();
                        }

                        // check for permanent denial of any permission
                        if (report.isAnyPermissionPermanentlyDenied()) {
                            // show alert dialog navigating to Settings

                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).
                withErrorListener(new PermissionRequestErrorListener() {
                    @Override
                    public void onError(DexterError error) {
                        Toast.makeText(getApplicationContext(), "Some Error! ", Toast.LENGTH_SHORT).show();
                    }
                })
                .onSameThread()
                .check();
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