package com.davidmerino.davidmagicapp.mapper

import com.davidmerino.davidmagicapp.model.GeoPoints
import com.davidmerino.domain.model.Location

fun Location.toGeoPoints() = GeoPoints(
    Latitude = lat,
    Longitude = lng,
    Name = name,
    address = address,
    city = city,
    phone = phone,
    postalCode = postalCode
)