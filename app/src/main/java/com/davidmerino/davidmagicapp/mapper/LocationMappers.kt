package com.davidmerino.davidmagicapp.mapper

import com.davidmerino.davidmagicapp.model.GeoPoints
import com.davidmerino.domain.model.Location

fun Location.toGeoPoints() = GeoPoints(
    lat = lat,
    long = lng,
    name = name,
    address = address,
    city = city,
    phone = phone,
    postalCode = postalCode
)