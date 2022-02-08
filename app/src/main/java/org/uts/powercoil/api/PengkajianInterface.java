package org.uts.powercoil.api;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface PengkajianInterface {
    String PDFURL = "http://152.69.196.49:8080/";
    @Multipart
    @POST("pengkajian.php")
    Call<String> uploadImage(
            @Part MultipartBody.Part file, @Part("filename") RequestBody name,
            @Part("nama") String nama,
            @Part("alamat") String alamat,
            @Part("pelaksana") String pelaksana
    );

}
