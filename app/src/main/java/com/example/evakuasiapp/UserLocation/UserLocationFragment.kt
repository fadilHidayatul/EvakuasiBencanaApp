package com.example.evakuasiapp.UserLocation

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.easywaylocation.EasyWayLocation
import com.example.easywaylocation.EasyWayLocation.LOCATION_SETTING_REQUEST_CODE
import com.example.easywaylocation.GetLocationDetail
import com.example.easywaylocation.Listener
import com.example.easywaylocation.LocationData
import com.example.easywaylocation.draw_path.DirectionUtil
import com.example.easywaylocation.draw_path.PolyLineDataBean
import com.example.evakuasiapp.MainActivity
import com.example.evakuasiapp.R
import com.example.evakuasiapp.SharedPreferences.PrefManager
import com.example.evakuasiapp.databinding.FragmentUserLocationBinding
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.*

/**
 * A simple [Fragment] subclass.
 */
class UserLocationFragment : Fragment(), Listener, LocationData.AddressCallBack{
    private lateinit var binding : FragmentUserLocationBinding
    private lateinit var manager : PrefManager

    private var easyLocation : EasyWayLocation? = null
    lateinit var getLocationDetail : GetLocationDetail
    var REQ_CODE : Int = 1

    private var lat : Double = 0.0
    private var lgt : Double = 0.0

    lateinit var gmaps : GoogleMap
    lateinit var cameraPosition: CameraPosition
    lateinit var center : LatLng
    lateinit var latLong : LatLng
    var markerOptions = MarkerOptions()

    private var wayPoints:ArrayList<LatLng> = ArrayList()


    fun UserLocationFragment() {
        // Required empty public constructor
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserLocationBinding.inflate(inflater, container, false)
        manager = PrefManager(context!!)

        var infobar : TextView = activity!!.findViewById(R.id.barInformasi)
        infobar.text = "Informasi Bencana"

        getLocationDetail = GetLocationDetail(this, requireContext())
        easyLocation = EasyWayLocation(requireContext(), false, this)

        binding.mapView.onCreate(savedInstanceState)
        binding.mapView.onResume()

        checkPermission(Manifest.permission.ACCESS_FINE_LOCATION,REQ_CODE)
        if (permissionIsGranted()) {
            getLocationUser()
        } else {

        }

        return binding.root
    }

    //check permission
    fun checkPermission(permission : String, requestCode: Int){
        if (ContextCompat.checkSelfPermission(requireContext(),permission) == PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(requireActivity() , arrayOf(permission) ,requestCode )
        }else{

        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == REQ_CODE){
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED ){
                Toast.makeText(requireContext(),
                    "Camera Permission Granted",
                    Toast.LENGTH_SHORT)
                    .show();
            }else{
                Toast.makeText(requireContext(),
                    "Location Permission Denied",
                    Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    ////grant permission & get location
    fun permissionIsGranted() : Boolean {
        var permissionState : Int = ActivityCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.ACCESS_FINE_LOCATION
        )

        return permissionState == PackageManager.PERMISSION_GRANTED
    }

    private fun getLocationUser() {
        easyLocation!!.startLocation()

    }

    ////implement Location Data
    override fun locationData(locationData: LocationData?) {
        binding.userLocation.text = locationData!!.full_address //ambil alamat
    }

    override fun locationOn() {
        Toast.makeText(requireContext(), "Location On", Toast.LENGTH_SHORT).show()
    }

    @SuppressLint("SetTextI18n")
    override fun currentLocation(location: Location?) {
        lat  = location!!.latitude
        lgt = location.longitude

        manager.setLat(manager.LAT, lat.toString())
        manager.setLong(manager.LONG, lgt.toString())

        center = LatLng(lat,lgt)  // ada latlong

        binding.userCoordinate.text = "$lat , $lgt" //ambil lat long
        getLocationDetail.getAddress(location.latitude, location.longitude, "com.google.android.geo.API_KEY")

        binding.mapView.getMapAsync(object : OnMapReadyCallback {
            override fun onMapReady(googleMaps: GoogleMap?) {
                gmaps = googleMaps!!

                center = LatLng(lat, lgt) //tidak dapat lat lgt
                cameraPosition = CameraPosition.Builder().target(center).zoom(16F).build()
                googleMaps.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
                latLong = LatLng(lat, lgt)

                gmaps.isIndoorEnabled = true
                var ui : UiSettings = gmaps.uiSettings
                ui.isIndoorLevelPickerEnabled = true


                var bitmap : Bitmap = BitmapFactory.decodeResource(resources, R.drawable.your_loc)
                var b  = Bitmap.createScaledBitmap(bitmap, 350,150,false)

                gmaps.clear()
                markerOptions.position(latLong)
                markerOptions.icon(BitmapDescriptorFactory.fromBitmap(b))
                markerOptions.title("Lokasi Sekarang")
                markerOptions.snippet("Live Location")

                gmaps.addMarker(markerOptions)
                gmaps.setOnInfoWindowClickListener {
                    Toast.makeText(context,"Lokasi saat ini",Toast.LENGTH_SHORT).show()
                }

                //route

            }


        })
    }

    override fun locationCancelled() {
        Toast.makeText(requireContext(), "Location Cancelled", Toast.LENGTH_SHORT).show()
    }

    ///Add-On Location
    override fun onResume() {
        super.onResume()
        easyLocation!!.startLocation()
        binding.mapView.onResume()
    }

    override fun onPause() {
        binding.mapView.onPause()
        super.onPause()
        easyLocation!!.endUpdates()
    }

    override fun onStart() {
        super.onStart()
        binding.mapView.onStart()
    }

    override fun onStop() {
        super.onStop()
        binding.mapView.onStop()
    }

    override fun onDestroy() {
        binding.mapView.onDestroy()
        super.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        binding.mapView.onLowMemory()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == LOCATION_SETTING_REQUEST_CODE){
            easyLocation!!.onActivityResult(resultCode)
        }
    }




}
