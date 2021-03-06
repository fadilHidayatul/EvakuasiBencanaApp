package com.example.evakuasiapp.JalurEvakuasi

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.evakuasiapp.R
import com.example.evakuasiapp.SharedPreferences.PrefManager
import com.example.evakuasiapp.databinding.ActivityMapEvakuasiBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.SphericalUtil

class MapEvakuasiActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMapEvakuasiBinding
    private lateinit var context: Context

    private lateinit var tempat : String
    private lateinit var alamat : String
    private lateinit var kecamatan : String
    private lateinit var lat : String
    private lateinit var long : String
    private lateinit var daya : String

    private lateinit var latUser : String
    private lateinit var longUser : String
    private lateinit var centerUser : LatLng

    private lateinit var gmaps : GoogleMap
    private lateinit var cameraPosition: CameraPosition
    private lateinit var center : LatLng
    private var markerOptions = MarkerOptions()

    private lateinit var manager : PrefManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapEvakuasiBinding.inflate(layoutInflater)
        setContentView(binding.root)
        context = this
        manager = PrefManager(context)
        latUser = manager.getLat().toString()
        longUser = manager.getLong().toString()

        var intent = intent
        tempat = intent.getStringExtra("tempat")!!
        alamat = intent.getStringExtra("alamat")!!
        kecamatan = intent.getStringExtra("kecamatan")!!
        lat = intent.getStringExtra("lat")!!
        long = intent.getStringExtra("long")!!
        daya = intent.getStringExtra("daya")!!

        binding.mapView.onCreate(savedInstanceState)
        binding.mapView.onResume()
        mapViewAsync()

        setDetailEvakuasi()
        getJarakEvakuasi()

        binding.showDirection.setOnClickListener(){
            var uri : String = String.format("https://maps.google.com/maps?q=$lat,$long")
            var intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
            startActivity(intent)
        }

    }

    private fun getJarakEvakuasi() {
        //teori algoritma djikstra
        var place1 : LatLng = LatLng(lat.toDouble(),long.toDouble())
        var place2 : LatLng = LatLng(latUser.toDouble(),longUser.toDouble())
        var jarak : Double? = null

        jarak = SphericalUtil.computeDistanceBetween(place1,place2)
        binding.jarakEvakuasi.text = String.format("%.2f",jarak/1000) + " km"
    }

    private fun mapViewAsync() {
        binding.mapView.getMapAsync(object :OnMapReadyCallback{
            override fun onMapReady(googleMaps: GoogleMap?) {
                gmaps = googleMaps!!
                center = LatLng(lat.toDouble(),long.toDouble())
                centerUser = LatLng(latUser.toDouble(),longUser.toDouble())
                cameraPosition = CameraPosition.Builder().target(center).zoom(13F).build()
                googleMaps.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))

                setPoint(center,"evakuasi")
                setPoint(centerUser,"user")

            }

        })
    }

    private fun setPoint(center: LatLng, s: String) {
        var bitmap : Bitmap = BitmapFactory.decodeResource(resources,R.drawable.run)
        var b = Bitmap.createScaledBitmap(bitmap,150,150,false)
        var bitmap2 : Bitmap = BitmapFactory.decodeResource(resources,R.drawable.your_loc)
        var b2 = Bitmap.createScaledBitmap(bitmap2,220,150,false)

        markerOptions.position(center)
        if (s == "evakuasi"){
            markerOptions.icon(BitmapDescriptorFactory.fromBitmap(b))
        }else if (s == "user"){
            markerOptions.icon(BitmapDescriptorFactory.fromBitmap(b2))
        }

        gmaps.addMarker(markerOptions)
    }

    private fun setDetailEvakuasi() {
        binding.tempat.text = tempat
        binding.kecamatan.text = "$alamat , $kecamatan"
        binding.daya.text = "$daya orang"

    }

    fun back(view: View) {
        finish()
        binding.mapView.onDestroy()
    }

//    override fun onResume() {
//        super.onResume()
//        binding.mapView.onResume()
//    }
//
//    override fun onStart() {
//        super.onStart()
//        binding.mapView.onStart()
//    }
//
//    override fun onStop() {
//        super.onStop()
//        binding.mapView.onStop()
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        binding.mapView.onDestroy()
//    }
//
//    override fun onLowMemory() {
//        super.onLowMemory()
//        binding.mapView.onLowMemory()
//    }




}
