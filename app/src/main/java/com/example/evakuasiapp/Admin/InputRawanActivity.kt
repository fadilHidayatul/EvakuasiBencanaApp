package com.example.evakuasiapp.Admin

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.evakuasiapp.databinding.ActivityInputRawanBinding

class InputRawanActivity : AppCompatActivity() {
    private lateinit var binding : ActivityInputRawanBinding
    private lateinit var context: Context
    var kategori = arrayOf("Longson","Banjir","Banjir Bandang")
    lateinit var pilKategori : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInputRawanBinding.inflate(layoutInflater)
        setContentView(binding.root)
        context = this

        var adapter = ArrayAdapter(context,android.R.layout.simple_spinner_item,kategori)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerKategori.adapter = adapter

        binding.spinnerKategori.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                pilKategori = kategori[p2]
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        binding.btnTambah.setOnClickListener {
            Toast.makeText(context,pilKategori, Toast.LENGTH_SHORT).show()
            var intent = Intent(context,DoneInputActivity::class.java)
            startActivity(intent)
        }


    }
}
