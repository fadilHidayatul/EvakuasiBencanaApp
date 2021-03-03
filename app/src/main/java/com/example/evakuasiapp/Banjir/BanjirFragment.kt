package com.example.evakuasiapp.Banjir

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast

import com.example.evakuasiapp.R
import com.example.evakuasiapp.UtilsApi.ApiClient
import com.example.evakuasiapp.databinding.FragmentBanjirBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.*
import okhttp3.ResponseBody
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 * Use the [BanjirFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BanjirFragment : Fragment() {

    private lateinit var binding : FragmentBanjirBinding

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
        binding = FragmentBanjirBinding.inflate(inflater,container,false)
        var infobar : TextView = activity!!.findViewById(R.id.barInformasi)
        infobar.text = "Titik Rawan"

        binding.mapView.onCreate(savedInstanceState)
        binding.mapView.onResume()

        AsyncMaps();

        return binding.root
    }

    private fun AsyncMaps() {
        binding.mapView.getMapAsync(object : OnMapReadyCallback{
            override fun onMapReady(googlemaps: GoogleMap?) {

                getPoint(googlemaps)

            }

        })
    }

    private fun getPoint(googlemaps: GoogleMap?) {
        ApiClient.getClient.getTitikBencana("2").enqueue(object : Callback<ResponseBody>{
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful){
                    var jsonO : JSONObject = JSONObject(response.body()!!.string())
                    if (jsonO.getString("status") == "200"){
                        var jsonA : JSONArray = jsonO.getJSONArray("DATA")
                        pos = LatLng(jsonA.getJSONObject(2).getString("lat").toDouble(),
                                    jsonA.getJSONObject(2).getString("long").toDouble())

                        //deklarasi map position
                        gmaps = googlemaps!!
                        cameraPosition = CameraPosition.Builder().target(pos).zoom(13F).build()
                        googlemaps.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))

                        //set marker maps berulang
                        for (i in 0 until jsonA.length()){
                            var data = jsonA.getJSONObject(i)
                            alamat = data.getString("alamat")
                            kecamatan = data.getString("kecamatan")
                            lat = data.getString("lat").toDouble()
                            long = data.getString("long").toDouble()

                            center = LatLng(lat,long)

                            setPoint(alamat,kecamatan,center)
                        }

                    }
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Toast.makeText(context,"Cek Koneksi Internet",Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun setPoint(alamat: String, kecamatan: String, center: LatLng) {
        var bitmap : Bitmap = BitmapFactory.decodeResource(resources, R.drawable.tsunami) //ganti icon
        var b : Bitmap = Bitmap.createScaledBitmap(bitmap,150,150,false)

        markerOptions.position(center)
        markerOptions.title(alamat)
        markerOptions.snippet(kecamatan)
        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(b))

        gmaps.addMarker(markerOptions)
    }

    override fun onStop() {
        super.onStop()
        binding.mapView.onStop()
    }


}
