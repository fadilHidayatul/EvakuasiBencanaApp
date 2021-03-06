package com.example.evakuasiapp.Tsunami

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.evakuasiapp.JalurEvakuasi.JalurEvakuasiActivity
import com.example.evakuasiapp.R
import com.example.evakuasiapp.UtilsApi.ApiClient
import com.example.evakuasiapp.databinding.FragmentTsunamiBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_tsunami.view.*
import okhttp3.ResponseBody
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * A simple [Fragment] subclass.
 */
class TsunamiFragment : Fragment() {
    private lateinit var binding : FragmentTsunamiBinding

    private var lat : Double = 0.0
    private var long : Double = 0.0
    private var kecamatan : String = ""
    private var alamat : String = ""

    private lateinit var gmaps : GoogleMap
    private lateinit var cameraPosition: CameraPosition
    private lateinit var center : LatLng
    private var markerOption = MarkerOptions()

    private lateinit var pos : LatLng

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTsunamiBinding.inflate(inflater, container, false)
        var infobar : TextView = activity!!.findViewById(R.id.barInformasi)
        infobar.text = "Titik Rawan Tsunami"

        binding.mapView.onCreate(savedInstanceState)
        binding.mapView.onResume()

        AsyncMapView()

        binding.btnJalurEvakuasiTsunami.setOnClickListener(){
            var intent = Intent(context,JalurEvakuasiActivity::class.java)
            intent.putExtra("kategori","5")
            startActivity(intent)
        }


        return binding.root
    }

    private fun AsyncMapView() {
        binding.mapView.getMapAsync(object : OnMapReadyCallback {
            override fun onMapReady(googleMaps: GoogleMap?) {

                getPoint(googleMaps)

            }

        })
    }

    private fun getPoint(googleMaps: GoogleMap?) {
        ApiClient.getClient.getTitikBencana("5").enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    var jsonO: JSONObject = JSONObject(response.body()!!.string())
                    if (jsonO.getString("status") == "200") {
                        var jsonA: JSONArray = jsonO.getJSONArray("DATA")
                        pos = LatLng(jsonA.getJSONObject(0).getString("lat").toDouble(),
                                    jsonA.getJSONObject(0).getString("long").toDouble())

                        //deklarasi map position
                        gmaps = googleMaps!!
//                   center = LatLng(-0.9272162, 100.349247)
                        cameraPosition = CameraPosition.Builder().target(pos).zoom(13F).build()
                        googleMaps.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))

                        for (i in 0 until jsonA.length()) {
                            var data: JSONObject = jsonA.getJSONObject(i)
                            alamat = data.getString("alamat")
                            kecamatan = data.getString("kecamatan")
                            lat = data.getString("lat").toDouble()
                            long = data.getString("long").toDouble()
                            center = LatLng(lat, long)

                            setPoint(alamat, kecamatan, center)

                        }
                    }
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Toast.makeText(context, "Koneksi Internet", Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun setPoint(alamat: String, kecamatan: String, center: LatLng) {

        var bitmap : Bitmap = BitmapFactory.decodeResource(resources, R.drawable.tsunami)
        var b  = Bitmap.createScaledBitmap(bitmap, 200,200,false)

        markerOption.position(center)
        markerOption.title(alamat)
        markerOption.snippet(kecamatan)
        markerOption.icon(BitmapDescriptorFactory.fromBitmap(b))

        gmaps.addMarker(markerOption)
    }

    override fun onStop() {
        super.onStop()
        binding.mapView.onStop()
    }


}
