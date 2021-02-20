package com.example.evakuasiapp.Admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.evakuasiapp.databinding.ActivityDoneInputBinding

class DoneInputActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDoneInputBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDoneInputBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}
