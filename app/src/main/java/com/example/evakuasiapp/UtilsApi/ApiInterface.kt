package com.example.evakuasiapp.UtilsApi

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.*

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
        @Field("tempat") tempat: String?,
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

    @FormUrlEncoded
    @POST("titik/get_titik_bencana.php")
    fun getTitikBencana(
        @Field("kategori") kategori: String?
    ) : Call<ResponseBody>

    @FormUrlEncoded
    @POST("evakuasi/get_tempat_evakuasi.php")
    fun  getEvakuasiBencana(
        @Field("kategori") kategori: String?
    ) : Call<ResponseBody>

    @FormUrlEncoded
    @POST("komentar/komentar.php")
    fun isiKomentar(
        @Field("isi") isi : String?,
        @Field("tgl") tanggal : String?,
        @Field("jam") jam :String?,
        @Field("kategori") kategori: String?
    ) : Call<ResponseBody>

    @GET("komentar/tampil_komentar.php")
    fun getKomentar(
        @Query("tanggal") tanggal: String?
    ) : Call<ResponseBody>

}