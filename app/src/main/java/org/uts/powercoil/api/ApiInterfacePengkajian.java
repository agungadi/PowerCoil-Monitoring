package org.uts.powercoil.api;

import org.uts.powercoil.model.PostPutDelPengkajian;
import org.uts.powercoil.model.PostPutDelPower;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.HTTP;
import retrofit2.http.PUT;

public interface ApiInterfacePengkajian {

    @FormUrlEncoded
    @PUT("pengkajian")
    Call<PostPutDelPengkajian> putPengkajian(@Field("id") String id,
                                             @Field("Nama") String Nama,
                                             @Field("Alamat") String Alamat,
                                             @Field("Pelaksana") String Pelaksana);
    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "pengkajian", hasBody = true)
    Call<PostPutDelPengkajian> deletePengkajian(@Field("id") String id);
}


