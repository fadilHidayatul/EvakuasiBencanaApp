package com.example.evakuasiapp.Admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.evakuasiapp.databinding.ActivityDoneInputBinding

class DoneInputActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDoneInputBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDoneInputBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnDone.setOnClickListener(){
            var intent : Intent = Intent(applicationContext,PilihInputActivity::class.java)
            intent.flags == Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }

    }

    override fun onBackPressed() {
    }
}
