package com.example.evakuasiapp.JalurEvakuasi

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.evakuasiapp.JalurEvakuasi.Adapter.JalurEvakuasiAdapter
import com.example.evakuasiapp.UtilsApi.ApiClient
import com.example.evakuasiapp.databinding.ActivityJalurEvakuasiBinding
import com.google.gson.Gson
import dmax.dialog.SpotsDialog
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class JalurEvakuasiActivity : AppCompatActivity() {
    private lateinit var binding : ActivityJalurEvakuasiBinding

    private var kategori : String = ""

    private lateinit var context: Context
    private lateinit var adapter : JalurEvakuasiAdapter
    private lateinit var dataEvakuasi : List<JalurEvakuasi.DATABean>

    private lateinit var dialog : SpotsDialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJalurEvakuasiBinding.inflate(layoutInflater)
        setContentView(binding.root)
        context = this
        dialog = SpotsDialog.Builder().setContext(context).setMessage("Harap Tunggu").setCancelable(false).build() as SpotsDialog

        val intent = intent
        kategori = intent.getStringExtra("kategori")!!

       binding.searchLokasi.queryHint = "Cari daerah"
        binding.searchLokasi.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                adapter.filter.filter(query)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                adapter.filter.filter(newText)
                return false
            }
        })

        getAllDataEvakuasi()

    }

    private fun getAllDataEvakuasi() {
        dialog.show()
        ApiClient.getClient.getEvakuasiBencana(kategori).enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    dialog.hide()
                    val jsonO = JSONObject(response.body()!!.string());
                    if (jsonO.getString("status") == "200") {

                        val jsonA = jsonO.getJSONArray("DATA")

                        val gson = Gson()
                        dataEvakuasi = ArrayList<JalurEvakuasi.DATABean>()

                        for (i in 0 until jsonA.length()) {
                            val ddata: JalurEvakuasi.DATABean = gson.fromJson(
                                jsonA.getJSONObject(i).toString(),
                                JalurEvakuasi.DATABean::class.java
                            )
                            (dataEvakuasi as ArrayList<JalurEvakuasi.DATABean>).add(ddata)
                        }

                        adapter = JalurEvakuasiAdapter(context, dataEvakuasi)
                        binding.recyclerBencana.adapter = adapter
                        binding.recyclerBencana.layoutManager = LinearLayoutManager(
                            context,
                            LinearLayoutManager.VERTICAL,
                            false
                        )
                        binding.recyclerBencana.setHasFixedSize(true)
                    } else {
                        Toast.makeText(
                            applicationContext,
                            jsonO.getString("message"),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    dialog.hide()
                    Toast.makeText(applicationContext, "REspon tidak berhasil", Toast.LENGTH_SHORT)
                        .show()
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                dialog.hide()
                Toast.makeText(applicationContext, "Koneksi Internet", Toast.LENGTH_SHORT).show()
            }

        })
    }

    fun backToPrevious(view: View) {
        finish()
    }

    override fun onPause() {
        super.onPause()
        dialog.dismiss()
    }

    override fun onDestroy() {
        super.onDestroy()
        dialog.dismiss()
    }


}

