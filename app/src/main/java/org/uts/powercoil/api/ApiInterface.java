package org.uts.powercoil.api;

import org.uts.powercoil.model.PostPutDelPower;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface ApiInterface {

//    @FormUrlEncoded
//    @POST("kontak")
//    Call<PostPutDelKontak> postKontak(@Field("nama") String nama,
//                                      @Field("nomor") String nomor);
    @FormUrlEncoded
    @PUT("powercoil")
    Call<PostPutDelPower> putKontak(@Field("id") String id,
                                    @Field("kapasitas") String kapasitas,
                                    @Field("terisi") String terisi,
                                    @Field("radiasi_matahari") String radiasi_matahari,
                                    @Field("jumlah_panel") String jumlah);
    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "powercoil", hasBody = true)
    Call<PostPutDelPower> deleteKontak(@Field("id") String id);
}
