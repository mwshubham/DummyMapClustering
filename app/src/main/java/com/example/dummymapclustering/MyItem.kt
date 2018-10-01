package com.example.dummymapclustering

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem

data class MyItem(
        private val mPosition: LatLng,
        private val mTitle: String,
        private val mSnippet: String
) : ClusterItem {

    constructor(lat: Double, lng: Double) : this(LatLng(lat, lng), "", "")

    override fun getPosition(): LatLng {
        return mPosition
    }

    override fun getTitle(): String {
        return mTitle
    }

    override fun getSnippet(): String {
        return mSnippet
    }
}