package com.example.dummymapclustering

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterManager

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var mClusterManager: ClusterManager<MyItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
//        val sydney = LatLng(28.584670, 77.352010)
//        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Noida"))
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))

        setUpCluster()
    }

    private fun setUpCluster() {
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(28.584670, 77.352010), 10f))

        // Initialize the manager with the context and the map.
        // (Activity extends context, so we can pass 'this' in the constructor.)
        mClusterManager = ClusterManager(this, mMap)
//        mClusterManager.setAnimation(false)

        // Point the map's listeners at the listeners implemented by the cluster
        // manager.
        mMap.setOnCameraIdleListener(mClusterManager)
        mMap.setOnMarkerClickListener(mClusterManager)

        // Add cluster items (markers) to the cluster manager.
        addItems()
    }

    private fun addItems() {
        var lat = 28.584670
        var lng = 77.352010

        for (i in 0..9) {
            val title = "#$i : This is the title"
            val snippet = "#$i : this is the snippet ."
            val offset = i / 60.0
            lat += offset
            lng += offset
            val offsetItem = MyItem(LatLng(lat, lng), title, snippet)
            mClusterManager.addItem(offsetItem)
        }
    }
}
