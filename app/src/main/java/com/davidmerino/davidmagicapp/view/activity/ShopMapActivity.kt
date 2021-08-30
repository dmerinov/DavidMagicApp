package com.davidmerino.davidmagicapp.view.activity

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.Toast
import com.davidmerino.davidmagicapp.R
import com.davidmerino.davidmagicapp.model.GeoPoints
import com.davidmerino.davidmagicapp.presenter.ShopMapPresenter
import com.davidmerino.davidmagicapp.presenter.ShopMapView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_search_shop.*
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

class ShopMapActivity : RootActivity<ShopMapView>(), ShopMapView {

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

    private var googleMap: GoogleMap? = null
    private val markersMap = mutableMapOf<Marker, GeoPoints>()

    override fun initializeUI() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        (mapFragment as SupportMapFragment).getMapAsync {
            googleMap = it
            registerMapListeners()
            presenter.onMapLoaded()
        }
    }

    override fun registerListeners() {
        //nothing to do
    }

    private fun registerMapListeners() {
        googleMap?.setOnMarkerClickListener { marker ->
            markersMap[marker]?.let { shop ->
                Toast.makeText(
                    this,
                    "the point that has been clicked is ${shop.name}",
                    Toast.LENGTH_LONG
                ).show()
            }.let { true }
        }
    }

    override fun loadPoints(points: List<GeoPoints>) {

        points.forEach { point ->
            googleMap?.addMarker(
                MarkerOptions().position(LatLng(point.lat, point.long)).title(point.name)
            )?.let {
                markersMap[it] = point
            }
        }

        googleMap?.moveCamera(
            CameraUpdateFactory.newLatLng(
                LatLng(
                    points.get(0).lat,
                    points.get(0).long
                )
            )
        )
        googleMap?.animateCamera(CameraUpdateFactory.zoomTo(15.0f))
    }

}