package com.example.evakuasiapp.Admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.evakuasiapp.databinding.ActivityPilihInputBinding

class PilihInputActivity : AppCompatActivity() {
    private lateinit var binding : ActivityPilihInputBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPilihInputBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnTitikRawan.setOnClickListener(){
            val intent : Intent = Intent(applicationContext,InputRawanActivity::class.java)
            startActivity(intent)
        }

        binding.btnJalurEvakuasi.setOnClickListener(){
            val intent : Intent = Intent(applicationContext,InputEvakuasiActivity::class.java)
            startActivity(intent)
        }
    }
}
