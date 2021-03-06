package com.example.evakuasiapp.BanjirBandang

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.example.evakuasiapp.JalurEvakuasi.JalurEvakuasiActivity
import com.example.evakuasiapp.R

import com.example.evakuasiapp.UtilsApi.ApiClient
import com.example.evakuasiapp.databinding.FragmentBanjirBandangBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.*
import okhttp3.ResponseBody
import org.json.JSONArray
import org.json.JSONObject
import org.w3c.dom.Text
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 */
class BanjirBandangFragment : Fragment() {
    private lateinit var binding : FragmentBanjirBandangBinding

    private var alamat : String = ""
    private var kecamatan : String = ""
    private var lat : Double = 0.0
    private var long : Double = 0.0

    private lateinit var gmaps : GoogleMap
    private lateinit var cameraPosition: CameraPosition
    private lateinit var center : LatLng
    private var markerOptions = MarkerOptions()

    private lateinit var pos : LatLng


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBanjirBandangBinding.inflate(inflater,container,false)

        var barInfo : TextView = activity!!.findViewById(R.id.barInformasi)
        barInfo.text = "Titik Rawan Banjir Bandang"

        binding.mapView.onCreate(savedInstanceState)
        binding.mapView.onResume()
        mapViewAsync()

        binding.btnJalurEvakuasiBanjirB.setOnClickListener(){
            var intent = Intent(context, JalurEvakuasiActivity::class.java)
            intent.putExtra("kategori","1")
            startActivity(intent)
        }

        return binding.root
    }

    private fun mapViewAsync() {
        binding.mapView.getMapAsync(object : OnMapReadyCallback{
            override fun onMapReady(googlemaps: GoogleMap?) {

                getPoint(googlemaps)

            }

        })
    }

    private fun getPoint(googlemaps: GoogleMap?) {
        ApiClient.getClient.getTitikBencana("1").enqueue(object : Callback<ResponseBody>{
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful){
                    var jsonO = JSONObject(response.body()!!.string())
                    if (jsonO.getString("status") == "200"){
                        var jsonA : JSONArray = jsonO.getJSONArray("DATA")

                        pos = LatLng(jsonA.getJSONObject(0).getString("lat").toDouble(),
                                    jsonA.getJSONObject(0).getString("long").toDouble())

                        gmaps = googlemaps!!
                        cameraPosition = CameraPosition.Builder().target(pos).zoom(13F).build()
                        googlemaps.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))

                        for (i in 0 until jsonA.length()){
                            var data = jsonA.getJSONObject(i)
                            alamat = data.getString("alamat")
                            kecamatan = data.getString("kecamatan")
                            lat = data.getString("lat").toDouble()
                            long = data.getString("long").toDouble()

                            center = LatLng(lat,long)

                            setPointToMap(alamat,kecamatan,center)
                        }
                    }else{
                        Toast.makeText(context,"${jsonO.getString("message")}",Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Toast.makeText(context,"Koneksi Internet",Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun setPointToMap(alamat: String, kecamatan: String, center: LatLng) {

        var bitmap : Bitmap = BitmapFactory.decodeResource(resources, R.drawable.banjir_bandang)
        var b = Bitmap.createScaledBitmap(bitmap,150,150,false)

        markerOptions.title(alamat)
        markerOptions.snippet(kecamatan)
        markerOptions.position(center)
        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(b))

        gmaps.addMarker(markerOptions)
    }




}
