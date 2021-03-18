package com.example.evakuasiapp.Admin

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.evakuasiapp.Admin.Adapter.ShowKomentarAdapter
import com.example.evakuasiapp.Admin.Model.Comment
import com.example.evakuasiapp.R
import com.example.evakuasiapp.UtilsApi.ApiClient
import com.example.evakuasiapp.databinding.ActivityShowKomentarBinding
import com.google.gson.Gson
import dmax.dialog.SpotsDialog
import okhttp3.ResponseBody
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

@Suppress("DEPRECATION")
class ShowKomentarActivity : AppCompatActivity() {
    private lateinit var binding : ActivityShowKomentarBinding
    private lateinit var context: Context

    lateinit var adapter : ShowKomentarAdapter
    lateinit var dataBean : List<Comment.DATABean>
    lateinit var dialog : SpotsDialog

    private lateinit var datePickerDialog: DatePickerDialog
    var DATE_ID : Int = 1
    private var th : Int = 0
    private var bln : Int = 0
    private var hr : Int = 0
    private var xmonth : Int = 0
    private var xyear : Int = 0
    private var xday : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowKomentarBinding.inflate(layoutInflater)
        setContentView(binding.root)
        context = this
        dialog = SpotsDialog.Builder().setContext(context).setCancelable(false).setMessage("Harap Tunggu").build() as SpotsDialog

        binding.getTgl.setOnClickListener {
            showDialog(DATE_ID)
        }

        val calendar = Calendar.getInstance()
        th = calendar.get(Calendar.YEAR)
        bln = calendar.get(Calendar.MONTH)
        hr = calendar.get(Calendar.DAY_OF_MONTH)

        datePickerDialog = DatePickerDialog(
            this, R.style.TimePickerTheme,
            { view, year, month, dayOfMonth ->
                xmonth = month + 1
                xyear = year
                xday = dayOfMonth
                var tgl : String = "$xyear-$xmonth-$xday"

                binding.selectedDate.text =  "$xday/" + formatMonth(xmonth.toString() + "") + "/$xyear"
                binding.selectedDate.alpha = 1F

                showComment(tgl)

            }, th, bln, hr
        )

        binding.getTgl.setOnClickListener {
            datePickerDialog.show()
        }

    }

    private fun showComment(tgl: String) {
        dialog.show()
        ApiClient.getClient.getKomentar(tgl).enqueue(object :Callback<ResponseBody>{
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful){
                    dialog.dismiss()
                    val jsonO = JSONObject(response.body()!!.string())
                    if (jsonO.getString("status") == "200"){
                        binding.layoutData.visibility = View.VISIBLE
                        binding.layoutNoData.visibility = View.GONE
                        val jsonA = jsonO.getJSONArray("DATA")

                        dataBean = ArrayList()
                        val gson = Gson()

                        for (i in 0 until jsonA.length()){
                            val dataIn : Comment.DATABean = gson.fromJson(jsonA.getJSONObject(i).toString(), Comment.DATABean::class.java)
                            (dataBean as ArrayList<Comment.DATABean>).add(dataIn)
                        }

                        adapter = ShowKomentarAdapter(context, dataBean)
                        binding.recyclerComment.adapter = adapter
                        binding.recyclerComment.layoutManager = LinearLayoutManager(context)
                        binding.recyclerComment.setHasFixedSize(true)
                    }else{
                        binding.layoutData.visibility = View.GONE
                        binding.layoutNoData.visibility = View.VISIBLE
                    }
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                dialog.dismiss()
                Toast.makeText(context,"Cek Koneksi Internet",Toast.LENGTH_SHORT).show()
            }

        })


    }

    private fun formatMonth(s: String): Any? {
        var monthParse = SimpleDateFormat("MM")
        var monthDisplay = SimpleDateFormat("MMMM")

        try {
            return monthDisplay.format(monthParse.parse(s))
        }catch (e : ParseException){
            e.printStackTrace()
        }
        return s
    }

    fun back(view: View) {
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
