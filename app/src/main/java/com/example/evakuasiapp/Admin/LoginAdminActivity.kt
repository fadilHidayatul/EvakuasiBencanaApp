package com.example.evakuasiapp.Admin

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.evakuasiapp.databinding.ActivityLoginAdminBinding

class LoginAdminActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginAdminBinding
    private lateinit var context : Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)
        context = this

        binding.btnLogin.setOnClickListener{
            var intent : Intent = Intent(context,InputEvakuasiActivity::class.java);
            startActivity(intent)
        }
    }
}
