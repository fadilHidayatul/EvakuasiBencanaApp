package com.example.evakuasiapp.Admin

import android.R.attr.data
import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.evakuasiapp.UtilsApi.ApiClient
import com.example.evakuasiapp.databinding.ActivityLoginAdminBinding
import com.google.gson.Gson
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginAdminActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginAdminBinding
    private lateinit var context : Context


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)
        context = this



        binding.btnLogin.setOnClickListener{
//            var intent : Intent = Intent(context, InputEvakuasiActivity::class.java)
//            startActivity(intent)
            loginAdmin()

        }
    }

    private fun loginAdmin() {
        if (TextUtils.isEmpty(binding.inputUsername.text.toString())){
            binding.inputUsername.error = "Masukkan Username"
        }else if (TextUtils.isEmpty(binding.inputPassword.text.toString())){
            binding.inputPassword.error = "Masukkan Password"
        }else{
            ApiClient.getClient.loginUser(
                binding.inputUsername.text.toString(),
                binding.inputPassword.text.toString()
            )
                .enqueue(object : Callback<ResponseBody> {
                    override fun onResponse(
                        call: Call<ResponseBody>,
                        response: Response<ResponseBody>
                    ) {
                        if (response.isSuccessful) {
                            val jsonO = JSONObject(response.body()!!.string())

                            if (jsonO.getString("status") == "200") {
                                val jsonO2 = jsonO.getJSONObject("DATA")

                                val gson = Gson()
                                val admin: Admin.DATABean = gson.fromJson(
                                    jsonO2.toString() ,
                                    Admin.DATABean::class.java
                                )




                            }
                        }
                    }

                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                        Toast.makeText(context, "Koneksi Internet", Toast.LENGTH_SHORT).show();
                    }

                })
        }
    }
}









