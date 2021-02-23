package com.example.evakuasiapp.UtilsApi

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiInterface {
    @FormUrlEncoded
    @POST("user/login.php")
    fun loginUser(
        @Field("username") username: String?,
        @Field("password") password: String?
    ): Call<ResponseBody>

    @FormUrlEncoded
    @POST("evakuasi/input_tempat_evakuasi.php")
    fun inputEvakuasi(
        @Field("shelter") shelter: String?,
        @Field("alamat") alamat: String?,
        @Field("kecamatan") kecamatan: String?,
        @Field("lat") lat: String?,
        @Field("lgt") lgt: String?,
        @Field("daya") daya: String?,
        @Field("kategori") kategori: String?
    ): Call<ResponseBody>

    @FormUrlEncoded
    @POST("titik/input_bencana.php")
    fun inputTitikRawan(
        @Field("alamat") alamat: String?,
        @Field("kecamatan") kecamatan: String?,
        @Field("lat") lat: String?,
        @Field("long") long : String?,
        @Field("kategori") kategori: String?
    ) : Call<ResponseBody>


}