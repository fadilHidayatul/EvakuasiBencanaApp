package com.example.evakuasiapp.Admin

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.evakuasiapp.databinding.ActivityInputEvakuasiBinding


class InputEvakuasiActivity : AppCompatActivity() {
    private lateinit var binding : ActivityInputEvakuasiBinding
    private lateinit var context : Context
    var kategori = arrayOf("Gempa", "Tsunami", "Longsor", "Banjir", "Banjir Bandang")
    lateinit var pilihan : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInputEvakuasiBinding.inflate(layoutInflater)
        setContentView(binding.root)
        context = this

        var adapter = ArrayAdapter(context,android.R.layout.simple_spinner_item, kategori)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerKategori.adapter = adapter

        binding.spinnerKategori.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                pilihan = kategori[p2]
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        binding.btnTambah.setOnClickListener {
            Toast.makeText(context,pilihan,Toast.LENGTH_SHORT).show()
            var intent = Intent(context,DoneInputActivity::class.java)
            startActivity(intent)
        }

    }
}
