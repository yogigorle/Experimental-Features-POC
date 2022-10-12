package com.example.experimentalfeaturespoc

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.SearchView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import bolts.CancellationToken
import com.example.experimentalfeaturespoc.databinding.ActivityMapsBinding
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.AutocompleteSessionToken
import com.google.android.libraries.places.api.model.RectangularBounds
import com.google.android.libraries.places.api.model.TypeFilter
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsResponse
import com.google.android.libraries.places.api.net.PlacesClient
import kotlinx.coroutines.delay
import timber.log.Timber
import java.util.*
import java.util.jar.Manifest

class MapsActivity : AppCompatActivity() {
    private lateinit var mapsActivityBinding: ActivityMapsBinding
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private val locationPermissions = arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION,
        android.Manifest.permission.ACCESS_FINE_LOCATION)
    private val locationRequestCode = 100
    private var latitude = 0.0
    private var longitude = 0.0
    private var findAutoCompletePredictionRequest: FindAutocompletePredictionsRequest.Builder? =
        null

    //for getting current location, only one ref per lifecycle need to be cancelled on onStop()
    private val cancellationTokenSource = CancellationTokenSource()
    private var placesClient: PlacesClient? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mapsActivityBinding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(mapsActivityBinding.root)

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        mapsActivityBinding.btnGetCurrentLoc.setOnClickListener {
            getLocation()
        }

        mapsActivityBinding.btnPinOnMap.setOnClickListener {
            val intent = Intent(this, MapWithMarkers::class.java)
            with(intent) {
                putExtra("LATITUDE", latitude)
                putExtra("LONGITUDE", longitude)
                startActivity(this)
            }
        }

        //CODE FOR AUTOCOMPLETE Search

        val autoCompleteSessionToken = AutocompleteSessionToken.newInstance()

        val bounds = RectangularBounds.newInstance(LatLng(17.202458, 78.241024),
            LatLng(17.603448, 78.677216))

        if (!Places.isInitialized()) {
            Places.initialize(this, BuildConfig.GOOGLE_PLACES_API_KEY)
        }

        placesClient = Places.createClient(this)

        findAutoCompletePredictionRequest =
            FindAutocompletePredictionsRequest.builder().setTypeFilter(TypeFilter.REGIONS)
                .setLocationRestriction(bounds).setSessionToken(autoCompleteSessionToken)


        mapsActivityBinding.productsSearchView.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {

//                    if (query.isNotEmpty() && query.count() > 2)
                //update into recent searches.
//                        callSearchProductsApi(query)
                return false
            }

            var count = 0
            override fun onQueryTextChange(newText: String): Boolean {
                if (newText.isNotEmpty()) {
                    setAutoCompleteAddressListener(newText)
                }
                return false
            }

        })

    }

    private fun setAutoCompleteAddressListener(query: String) {
        placesClient?.run {
            findAutoCompletePredictionRequest?.let {
                this.findAutocompletePredictions(it.setQuery(query).build())
                    .addOnSuccessListener { response: FindAutocompletePredictionsResponse ->
                        for (prediction in response.autocompletePredictions) {
                            Timber.e(prediction.placeId)
                            Timber.e(prediction.getPrimaryText(null).toString())
                        }
                    }.addOnFailureListener { exception: Exception? ->
                        if (exception is ApiException) {
                            Timber.e("Place not found: " + exception.statusCode)
                        }
                    }

            }
        }
    }

    //check whether location is enabled or not in the device
    private fun isLocationEnabled(): Boolean {
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER)
    }

    private fun requestLocationPermissions() {
        ActivityCompat.requestPermissions(this, locationPermissions, locationRequestCode)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == locationRequestCode && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            //get the location.
            getLocation()
        }
    }

    @SuppressLint("MissingPermission")
    private fun getLocation() {
        if (checkPermissions(locationPermissions)) {
            if (isLocationEnabled()) {
                fusedLocationProviderClient.lastLocation.addOnCompleteListener { task ->
                    if (task.isSuccessful && task.result != null) {
                        val result = task.result
                        latitude = result.latitude
                        longitude = result.longitude
                        val addressList =
                            Geocoder(this, Locale.getDefault()).getFromLocation(result.latitude,
                                result.longitude,
                                1)
                        addressList?.let {
                            with(mapsActivityBinding) {
                                tvLongitudeInfo.text = it[0].longitude.toString()
                                tvLatitudeInfo.text = it[0].latitude.toString()
                                tvLocalityInfo.text = it[0].locality.toString()
                                tvAddressInfo.text = it[0].getAddressLine(0)
                            }
                        }
                    }
                }
            } else {
                showToast("Please Enable Your Location...")
                startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
            }
        } else {
            showToast("Please Accept Location Permissions...")
            requestLocationPermissions()
        }
    }

    //only if last location is not present.
    private fun getNewLocation() {

    }

    override fun onStop() {
        super.onStop()
        cancellationTokenSource.cancel()
    }
}