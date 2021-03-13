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
import com.example.evakuasiapp.R
import com.example.evakuasiapp.SharedPreferences.PrefManager
import com.example.evakuasiapp.UtilsApi.ApiClient
import com.example.evakuasiapp.databinding.ActivityInputEvakuasiBinding
import com.tapadoo.alerter.Alerter
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class InputEvakuasiActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInputEvakuasiBinding
    private lateinit var context: Context
    private lateinit var manager: PrefManager

    var kategori = arrayOf(
        "Pilih Kategori",
        "Gempa",
        "Tsunami"
    )
    var no = arrayOf("0", "3", "5")
    lateinit var pilihan: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInputEvakuasiBinding.inflate(layoutInflater)
        setContentView(binding.root)
        context = this
        manager = PrefManager(this)

        var adapter = ArrayAdapter(context, android.R.layout.simple_spinner_item, kategori)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerKategori.adapter = adapter

        binding.spinnerKategori.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                for (i in kategori.indices) {
                    pilihan = no[p2]
                }

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }


        binding.btnTambah.setOnClickListener {
            if (pilihan == "0") {
                Alerter.create(this)
                    .setText("Pilih kategori terlebih dahulu")
                    .setBackgroundColorRes(R.color.red).setIcon(R.drawable.ic_warning).show()
            } else {
                inputEvakuasi()
            }
        }

    }

    private fun inputEvakuasi() {
        ApiClient.getClient.inputEvakuasi(
            binding.inputTempat.text.toString(),
            binding.inputAlamat.text.toString(),
            binding.inputKecamatan.text.toString(),
            binding.inputLatitude.text.toString(),
            binding.inputLongitude.text.toString(),
            binding.inputTampung.text.toString(),
            pilihan
        ).enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    val jsonO = JSONObject(response.body()!!.string());

                    if (jsonO.getString("status") == "200") {

                        var intent = Intent(context, DoneInputActivity::class.java)
                        startActivity(intent)

                    } else {
                        Toast.makeText(applicationContext, "Gagal Input", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Toast.makeText(applicationContext, "Koneksi Internet", Toast.LENGTH_SHORT).show()
            }

        })

    }
}

private fun <T> Call<T>.enqueue(any: Any) {
    TODO("Not yet implemented")
}
