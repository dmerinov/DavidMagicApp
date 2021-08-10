package com.davidmerino.data.model.cardVO

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class CardVO(
    @PrimaryKey
    open var id: String = "",

    var imageUrl: String = "",
    var manaCost: String = "",
    var multiverseid: String = "",
    var name: String = "",
    var set: String = "",
    var text: String = "",
    var toughness: String = "",
    var power: String = ""
) : RealmObject()