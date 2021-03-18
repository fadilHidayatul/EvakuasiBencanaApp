package com.example.evakuasiapp.Kontak

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.evakuasiapp.databinding.ActivityKontakBinding

class KontakActivity : AppCompatActivity() {
    private lateinit var binding : ActivityKontakBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKontakBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.txtAddress.text = "Jln. Gunuang Pangilun no 42 H"
        binding.txtHP.text = "+62 823 9101 1027\nhelloearthquake@gmail.com"

        binding.backButton.setOnClickListener(){
            finish()
        }
    }


}
