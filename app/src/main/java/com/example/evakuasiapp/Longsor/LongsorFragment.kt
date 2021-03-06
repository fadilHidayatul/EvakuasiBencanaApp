package com.example.evakuasiapp.Longsor

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
import com.example.evakuasiapp.databinding.FragmentLongsorBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * A simple [Fragment] subclass.
 */
class LongsorFragment : Fragment() {
    private lateinit var binding : FragmentLongsorBinding

    private lateinit var gmaps : GoogleMap
    private lateinit var cameraPosition: CameraPosition
    private lateinit var center : LatLng
    private var markerOptions = MarkerOptions()

    private lateinit var pos : LatLng

    private var alamat : String = ""
    private var kecamatan : String = ""
    private var lat : Double = 0.0
    private var long : Double = 0.0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLongsorBinding.inflate(inflater,container,false)
        var barInfo : TextView = activity!!.findViewById(R.id.barInformasi)
        barInfo.text = "Titik Rawan Longsor"

        binding.mapView.onCreate(savedInstanceState)
        binding.mapView.onResume()
        AsyncMapView()

        binding.btnJalurEvakuasiLongsor.setOnClickListener(){
            var intent = Intent(context, JalurEvakuasiActivity::class.java)
            intent.putExtra("kategori","4")
            startActivity(intent)
        }

        return binding.root
    }

    private fun AsyncMapView() {
        binding.mapView.getMapAsync(object : OnMapReadyCallback{
            override fun onMapReady(googlemaps: GoogleMap?) {
                getPoint(googlemaps)
            }

        })
    }

    private fun getPoint(googlemaps: GoogleMap?) {
        ApiClient.getClient.getTitikBencana("4").enqueue(object :Callback<ResponseBody>{
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful){
                    var jsonO = JSONObject(response.body()!!.string())
                    if (jsonO.getString("status") == "200"){
                        var jsonA = jsonO.getJSONArray("DATA")

                        pos = LatLng(jsonA.getJSONObject(0).getString("lat").toDouble(),
                                    jsonA.getJSONObject(0).getString("long").toDouble())

                        gmaps = googlemaps!!
                        cameraPosition = CameraPosition.Builder().target(pos).zoom(12F).build()
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
                    }
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Toast.makeText(context,"Cek Koneksi Internet",Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun setPointToMap(alamat: String, kecamatan: String, center: LatLng) {
        var bitmap : Bitmap = BitmapFactory.decodeResource(resources, R.drawable.longsor)
        var b = Bitmap.createScaledBitmap(bitmap,200,200,false)

        markerOptions.title(alamat)
        markerOptions.snippet(kecamatan)
        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(b))
        markerOptions.position(center)

        gmaps.addMarker(markerOptions)
    }


}
