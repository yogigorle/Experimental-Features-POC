package com.example.experimentalfeaturespoc

import android.annotation.SuppressLint
import android.location.Address
import android.location.Geocoder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import com.example.experimentalfeaturespoc.databinding.ActivityMapWithMarkersBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import java.lang.Exception
import java.util.*

class MapWithMarkers : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mapWithMarkersBinding: ActivityMapWithMarkersBinding
    private val locationPermissions = arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION,
        android.Manifest.permission.ACCESS_FINE_LOCATION)

    private var latitude = 0.0
    private var longitude = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mapWithMarkersBinding = ActivityMapWithMarkersBinding.inflate(layoutInflater)
        setContentView(mapWithMarkersBinding.root)

        intent?.let {
            latitude = it.getDoubleExtra("LATITUDE", 0.0)
            longitude = it.getDoubleExtra("LONGITUDE", 0.0)
        }


        val mapFragment =
            supportFragmentManager.findFragmentById(R.id.map_fragment) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }

    private fun requestLocationPermissions() {
        ActivityCompat.requestPermissions(this, locationPermissions, 0)
    }

    @SuppressLint("PotentialBehaviorOverride", "MissingPermission")
    override fun onMapReady(gmap: GoogleMap) {
        with(gmap) {
            if (checkPermissions(locationPermissions)) {

            } else {
                requestLocationPermissions()
            }

        }
    }

    private fun getAddressFromLatLong(latLng: LatLng): Address? {
        val geocoder = Geocoder(this, Locale.getDefault())
        return try {
            geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1)?.get(0)
        } catch (e: Exception) {
            null
        }
    }
}