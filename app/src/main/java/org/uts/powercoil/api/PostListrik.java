package org.uts.powercoil.api;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface PostListrik {
    String LISTRIKURL = "http://152.69.196.49:8080/";
    @FormUrlEncoded
    @POST("listrik.php")
    Call<String> getlISTRIK(
            @Field("nama") String nama,
            @Field("kapasitas") String kapasitas,
            @Field("terisi") String terisi,
            @Field("arus_dc") String arus,
            @Field("gambar_url") String image,
            @Field("radiasi_matahari") String radiasi,
            @Field("deskripsi") String deskripsi,
            @Field("jumlah_panel") String jumlah,
            @Field("kordinat") String kordinat
    );
}
