package com.davidmerino.davidmagicapp.view.activity

import android.content.Context
import android.content.Intent
import android.view.View
import com.davidmerino.davidmagicapp.R
import com.davidmerino.davidmagicapp.model.GeoPoints
import com.davidmerino.davidmagicapp.presenter.ShopMapPresenter
import com.davidmerino.davidmagicapp.presenter.ShopMapView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_search_shop.*
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

class ShopMapActivity : RootActivity<ShopMapView>(), ShopMapView, OnMapReadyCallback {

    companion object {
        private const val SHOP_MAP_KEY = "SHOP_MAP_KEY"

        fun getCallingIntent(context: Context) =
            Intent(context, ShopMapActivity::class.java)
    }

    override val progress: View
        get() = TODO("Not yet implemented")

    override val presenter: ShopMapPresenter by instance()

    override val layoutResourceId: Int = R.layout.activity_search_shop

    override val activityModule: Kodein.Module = Kodein.Module {
        bind<ShopMapPresenter>() with provider {
            ShopMapPresenter(
                errorHandler = instance(),
                view = this@ShopMapActivity,
                shopsUseCase = instance()
            )
        }
    }

    private lateinit var map: GoogleMap

    override fun initializeUI() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun registerListeners() {
        //nothing to do
    }

    override fun onMapReady(googleMap: GoogleMap) {
        presenter.onMapLoaded(googleMap)
    }

    override fun loadPoints(points: List<GeoPoints>, googleMap: GoogleMap) {
        map = googleMap

        points.forEach { point ->
            map.addMarker(
                MarkerOptions().position(LatLng(point.Latitude, point.Longitude)).title(point.Name)
            )
        }

        map.moveCamera(
            CameraUpdateFactory.newLatLng(
                LatLng(
                    points.get(0).Latitude,
                    points.get(0).Longitude
                )
            )
        )
        map.animateCamera(CameraUpdateFactory.zoomTo(15.0f))


    }

}