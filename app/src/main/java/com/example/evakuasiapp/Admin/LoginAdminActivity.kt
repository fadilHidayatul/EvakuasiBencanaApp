package com.example.evakuasiapp.Admin

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import com.example.evakuasiapp.MainActivity
import com.example.evakuasiapp.R
import com.example.evakuasiapp.SharedPreferences.PrefManager
import com.example.evakuasiapp.UtilsApi.ApiClient
import com.example.evakuasiapp.databinding.ActivityLoginAdminBinding
import com.google.gson.Gson
import com.tapadoo.alerter.Alerter
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginAdminActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginAdminBinding
    private lateinit var context : Context
    private lateinit var manager : PrefManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)
        context = this
        manager = PrefManager(this)

        binding.btnLogin.setOnClickListener{
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
                                    jsonO2.toString(),
                                    Admin.DATABean::class.java
                                )

                                manager.saveSession()
                                manager.setUsername(manager.USERNAME, admin.username)
                                manager.setToken(manager.TOKEN, admin.token)

                                var intent: Intent = Intent(
                                    context,
                                    PilihInputActivity::class.java
                                )
                                startActivity(intent)
                            } else {
                                Alerter.create(this@LoginAdminActivity)
                                    .setText(jsonO.getString("message"))
                                    .setIcon(R.drawable.ic_warning).setBackgroundColorRes(R.color.red).show()
                            }
                        }
                    }

                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                        Alerter.create(this@LoginAdminActivity)
                            .setTitle("Warning")
                            .setText("Koneksi Internet")
                            .setIcon(R.drawable.ic_warning)
                            .setBackgroundColorRes(R.color.red)
                            .show()
                    }

                })
        }
    }

    override fun onStart() {
        super.onStart()
        var manager = PrefManager(this)
        var userID : Boolean = manager.getSession()

        if (userID){
            var intent : Intent = Intent(applicationContext, PilihInputActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        var intent : Intent = Intent(applicationContext, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
        finish()
    }


}









