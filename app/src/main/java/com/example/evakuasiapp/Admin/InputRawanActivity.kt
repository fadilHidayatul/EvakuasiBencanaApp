package com.example.evakuasiapp.Admin

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.evakuasiapp.R
import com.example.evakuasiapp.UtilsApi.ApiClient
import com.example.evakuasiapp.databinding.ActivityInputRawanBinding
import com.tapadoo.alerter.Alerter
import kotlinx.android.synthetic.main.activity_input_evakuasi.*
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InputRawanActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInputRawanBinding
    private lateinit var context: Context
    var kategori = arrayOf("Pilih Kategori", "Longsor", "Banjir", "Banjir Bandang")
    var no = arrayOf("0", "4", "2", "1")
    lateinit var pilKategori: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInputRawanBinding.inflate(layoutInflater)
        setContentView(binding.root)
        context = this

        var adapter = ArrayAdapter(context, android.R.layout.simple_spinner_item, kategori)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerKategori.adapter = adapter

        binding.spinnerKategori.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    for (i in kategori.indices) {
                        pilKategori = no[p2]
                    }
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }

            }

        binding.btnTambah.setOnClickListener {
            if (pilKategori == "0") {
                Alerter.create(this)
                    .setText("Pilih kategori terlebih dahulu")
                    .setIcon(R.drawable.ic_warning).setBackgroundColorRes(R.color.red).show()
            } else {
                sendTitikRawan()
            }

        }

    }

    private fun sendTitikRawan() {
        ApiClient.getClient.inputTitikRawan(
            binding.inputAlamat.text.toString(),
            binding.inputKecamatan.text.toString(),
            binding.inputLatitude.text.toString(),
            binding.inputLongitude.text.toString(),
            pilKategori
        ).enqueue(object : Callback<ResponseBody>{
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful){
                    val jsonO = JSONObject(response.body()!!.string());

                    if (jsonO.getString("status") == "200"){
                        var intent = Intent(context,DoneInputActivity::class.java)
                        startActivity(intent)

                    }else{
                        Toast.makeText(applicationContext, "Gagal Input", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Toast.makeText(applicationContext, "Koneksi Internet ", Toast.LENGTH_SHORT).show()
            }

        })
    }

}




      