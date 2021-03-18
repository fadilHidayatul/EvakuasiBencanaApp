package com.example.evakuasiapp.Admin

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.evakuasiapp.MainActivity
import com.example.evakuasiapp.SharedPreferences.PrefManager
import com.example.evakuasiapp.databinding.ActivityPilihInputBinding

class PilihInputActivity : AppCompatActivity() {
    private lateinit var binding : ActivityPilihInputBinding
    private lateinit var context : Context
    private lateinit var manager : PrefManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPilihInputBinding.inflate(layoutInflater)
        setContentView(binding.root)
        context = this

        manager = PrefManager(context)

        binding.btnTitikRawan.setOnClickListener(){
            val intent : Intent = Intent(applicationContext,InputRawanActivity::class.java)
            startActivity(intent)
        }

        binding.btnJalurEvakuasi.setOnClickListener(){
            val intent : Intent = Intent(applicationContext,InputEvakuasiActivity::class.java)
            startActivity(intent)
        }

        binding.btnShowKomentar.setOnClickListener{
            val intent = Intent(applicationContext,ShowKomentarActivity::class.java)
            startActivity(intent)
        }

        binding.btnLogout.setOnClickListener(){
            manager.removeSession()
            finish()
            val intent : Intent = Intent(applicationContext, LoginAdminActivity::class.java)
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
