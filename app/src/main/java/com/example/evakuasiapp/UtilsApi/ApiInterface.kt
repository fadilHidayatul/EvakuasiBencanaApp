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
        @Field("username") username : String?,
        @Field("password") password : String?
    ) : Call<ResponseBody>

}