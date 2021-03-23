package com.example.evakuasiapp.Gempa

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.example.evakuasiapp.JalurEvakuasi.JalurEvakuasiActivity

import com.example.evakuasiapp.R
import com.example.evakuasiapp.SharedPreferences.PrefManager
import com.example.evakuasiapp.UtilsApi.ApiClient
import com.example.evakuasiapp.databinding.FragmentGempaBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.SphericalUtil
import kotlinx.android.synthetic.main.fragment_gempa.*
import kotlinx.android.synthetic.main.fragment_gempa.view.*
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 */
class GempaFragment : Fragment() {
    private lateinit var binding : FragmentGempaBinding
    private lateinit var manager : PrefManager

    private lateinit var latUser : String
    private lateinit var longUser : String

    private lateinit var gmaps : GoogleMap
    private lateinit var cameraPosition: CameraPosition
    private lateinit var center : LatLng
    private var markerOptions = MarkerOptions()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGempaBinding.inflate(inflater,container,false)
        manager = PrefManager(context!!)
        latUser = manager.getLat().toString()
        longUser = manager.getLong().toString()

        var toolbar : TextView = activity!!.findViewById(R.id.barInformasi)
        toolbar.text = "Titik Rawan Gempa"

        binding.mapView.onCreate(savedInstanceState)
        binding.mapView.onResume()
        showMap()

//        var lt : Double = manager.getLat()!!.toDouble()
//        var lg : Double = manager.getLong()!!.toDouble()
//        Toast.makeText(requireContext(), "$lt , $lg", Toast.LENGTH_SHORT).show()

        binding.btnEvakuasiGempa.setOnClickListener {
            var intent = Intent(context,JalurEvakuasiActivity::class.java)
            intent.putExtra("kategori","3")
            startActivity(intent)
        }

        getDataEvakuasi()

        return binding.root
    }

    private fun showMap() {
        binding.mapView.getMapAsync(object : OnMapReadyCallback{
            override fun onMapReady(p0: GoogleMap?) {
                gmaps = p0!!
                center = LatLng(-0.9447304,100.3733914)
                cameraPosition = CameraPosition.Builder().target(center).zoom(12F).build()
                p0.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
            }

        })
    }

    private fun getDataEvakuasi() {
        ApiClient.getClient.getEvakuasiBencana("3").enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    val jsonO = JSONObject(response.body()!!.string());
                    if (jsonO.getString("status") == "200") {

                        val jsonA = jsonO.getJSONArray("DATA")

                        for (i in 0 until jsonA.length()) {
                            val lat : String = jsonA.getJSONObject(i).getString("lat")
                            val long : String = jsonA.getJSONObject(i).getString("long")
                            val id : String = jsonA.getJSONObject(i).getString("id")

                            var place1 = LatLng(lat.toDouble(),long.toDouble())
                            var place2 = LatLng(latUser.toDouble(),longUser.toDouble())
                            var distance : Double? =  SphericalUtil.computeDistanceBetween(place1,place2)

                            updateJarakEvakuasi(distance, id)
                        }
                    } else {
                        Toast.makeText(
                            context,
                            jsonO.getString("message"),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    Toast.makeText(context, "REspon tidak berhasil", Toast.LENGTH_SHORT)
                        .show()
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Toast.makeText(context, "Koneksi Internet", Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun updateJarakEvakuasi(jarak: Double?, id: String) {
        ApiClient.getClient.updateJarak(jarak.toString(),id).enqueue(object :
            Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful){
                    var jsonO = JSONObject(response.body()!!.string())
                    if (jsonO.getString("status") == "200"){
                        //
                    }else{
                        Toast.makeText(context, jsonO.getString("message"), Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Toast.makeText(context, "Cek Koneksi Internet", Toast.LENGTH_SHORT).show()
            }

        })
    }


}
