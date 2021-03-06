package com.example.evakuasiapp.Offline

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.evakuasiapp.Offline.Adapter.OfflineAdapter
import com.example.evakuasiapp.Offline.Model.*
import com.example.evakuasiapp.R
import com.example.evakuasiapp.databinding.ActivityOfflineBinding
import com.example.evakuasiapp.databinding.ActivityOfflineEvakuasiBinding

class OfflineEvakuasiActivity : AppCompatActivity() {
    private lateinit var binding : ActivityOfflineEvakuasiBinding
    private lateinit var context: Context
    private lateinit var kategori : String

    private var list : ArrayList<Offline> = arrayListOf()
    private lateinit var adapter : OfflineAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOfflineEvakuasiBinding.inflate(layoutInflater)
        setContentView(binding.root)
        context = this

        var intent = intent
        kategori = intent.getStringExtra("kategori")!!

        if (kategori == "gempa"){
            list.addAll(OfflineGempa.listDataGempa) //list model diisi dengan object
        }else if (kategori == "tsunami"){
            list.addAll(OfflineTsunami.listTsunami)
        }else if (kategori == "longsor"){
            list.addAll(OfflineLongsor.listData)
        }else if (kategori == "banjir"){
            list.addAll(OfflineBanjir.listBanjir)
        }else if (kategori == "bandang"){
            Toast.makeText(context,"bb",Toast.LENGTH_SHORT).show()
        }

        showRecyclerOffline()

    }

    private fun showRecyclerOffline() {
        adapter = OfflineAdapter(context,list)
        binding.recyclerOffline.adapter = adapter
        binding.recyclerOffline.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        binding.recyclerOffline.setHasFixedSize(true)
    }
}
