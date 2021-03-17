package com.example.evakuasiapp.Komentar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.evakuasiapp.Admin.PilihInputActivity
import com.example.evakuasiapp.MainActivity
import com.example.evakuasiapp.R
import com.example.evakuasiapp.databinding.ActivityKomentarBinding
import com.example.evakuasiapp.databinding.ActivitySelesaiKomentarBinding

class SelesaiKomentarActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySelesaiKomentarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelesaiKomentarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSelesai.setOnClickListener(){
            var intent : Intent = Intent(applicationContext, MainActivity::class.java)
            intent.flags == Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }
    }
}
