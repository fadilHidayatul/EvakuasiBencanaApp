package com.example.evakuasiapp.Offline

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.evakuasiapp.databinding.ActivityOfflineBinding

class OfflineActivity : AppCompatActivity() {
    private lateinit var binding : ActivityOfflineBinding
    private lateinit var context : Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOfflineBinding.inflate(layoutInflater)
        setContentView(binding.root)
        context = this

        binding.pilihGempa.setOnClickListener(){
            var intent = Intent(context,OfflineEvakuasiActivity::class.java)
            intent.putExtra("kategori","gempa")
            startActivity(intent)
        }
        binding.pilihTsunami.setOnClickListener(){
            var intent = Intent(context,OfflineEvakuasiActivity::class.java)
            intent.putExtra("kategori","tsunami")
            startActivity(intent)
        }
        binding.pilihLongsor.setOnClickListener(){
            var intent = Intent(context,OfflineEvakuasiActivity::class.java)
            intent.putExtra("kategori","longsor")
            startActivity(intent)
        }
        binding.pilihBanjir.setOnClickListener(){
            var intent = Intent(context,OfflineEvakuasiActivity::class.java)
            intent.putExtra("kategori","banjir")
            startActivity(intent)
        }
        binding.pilihBandang.setOnClickListener(){
            var intent = Intent(context,OfflineEvakuasiActivity::class.java)
            intent.putExtra("kategori","bandang")
            startActivity(intent)
        }

    }

    fun backToMain(view: View) {
        finish()
    }
}
