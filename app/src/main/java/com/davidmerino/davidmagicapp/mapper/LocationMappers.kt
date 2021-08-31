package com.davidmerino.davidmagicapp.mapper

import com.davidmerino.davidmagicapp.model.ShopView
import com.davidmerino.domain.model.Shop

fun Shop.toGeoPoints() = ShopView(
    id = id,
    lat = lat,
    long = lng,
    name = name,
    address = address,
    city = city,
    phone = phone,
    postalCode = postalCode,
    isFavourite = isFav
)

fun ShopView.toShop() = Shop(
    id = id,
    lat = lat,
    lng = long,
    name = name,
    address = address,
    city = city,
    phone = phone,
    postalCode = postalCode,
    isFav = isFavourite
)