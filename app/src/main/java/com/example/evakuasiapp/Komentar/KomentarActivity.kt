package com.example.evakuasiapp.Komentar

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.evakuasiapp.Admin.DoneInputActivity
import com.example.evakuasiapp.MainActivity
import com.example.evakuasiapp.R
import com.example.evakuasiapp.UtilsApi.ApiClient
import com.example.evakuasiapp.databinding.ActivityKomentarBinding
import com.tapadoo.alerter.Alerter
import dmax.dialog.SpotsDialog
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class KomentarActivity : AppCompatActivity() {
    private lateinit var binding : ActivityKomentarBinding
    private lateinit var context: Context

    lateinit var tanggal : String
    lateinit var jam : String
    var kategori = arrayOf("Pilih Kategori","Banjir Bandang", "Banjir", "Gempa","Longsor","Tsunami")
    var no = arrayOf("0","1","2","3","4","5")
    lateinit var pilKategori : String

    private lateinit var dialog : SpotsDialog



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKomentarBinding.inflate(layoutInflater)
        setContentView(binding.root)
        context = this
        dialog = SpotsDialog.Builder().setContext(context).setMessage("Harap Tunggu").setCancelable(false).build() as SpotsDialog

        var dateFormat = SimpleDateFormat("yyyy-MM-dd")
        tanggal  = dateFormat.format(Date())

        var hourFormat = SimpleDateFormat("HH:mm:ss")
        jam  = hourFormat.format(Date())

        var adapter  = ArrayAdapter(context, android.R.layout.simple_spinner_item,kategori)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerKategori.adapter = adapter

        binding.spinnerKategori.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                for (i in kategori.indices){
                    pilKategori = no[p2]
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        binding.btnKirimKomentar.setOnClickListener{
            if (pilKategori == "0"){
                Alerter.create(this)
                    .setText("Pilih kategori terlebih dahulu")
                    .setIcon(R.drawable.ic_warning).setBackgroundColorRes(R.color.red).show()
            }else{
                sendKomentar()
            }

        }

    }

    private fun sendKomentar() {
        dialog.show()
        ApiClient.getClient.isiKomentar(
            binding.inputKomentar.text.toString(),
            tanggal,
            jam,
            pilKategori).
        enqueue(object :Callback<ResponseBody>{
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful){
                    dialog.dismiss()
                    var jsonO = JSONObject(response.body()!!.string())
                    if (jsonO.getString("status") == "200"){

                        var intent = Intent(context, SelesaiKomentarActivity::class.java)
                        startActivity(intent)

                    }
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                dialog.dismiss()
                Alerter.create(this@KomentarActivity).setTitle("Warning").setText("Cek Koneksi Internet untuk mengirim komentar")
                    .setIcon(R.drawable.ic_warning).setBackgroundColorRes(R.color.red).show()
            }

        })
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
