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


    }
}









