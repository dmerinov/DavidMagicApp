package com.davidmerino.data.datasource.local

import android.content.Context
import com.davidmerino.data.mapper.toCardVO
import com.davidmerino.data.model.card.CardDto
import com.davidmerino.data.model.cardVO.CardVO
import com.google.gson.Gson
import io.realm.Realm
import io.realm.RealmConfiguration

class RealmDatabase(context: Context) : Local {

    companion object {
        private const val CARD_LIST = "REALM_CARD_LIST"
    }

    private val realmName: String = "Database"
    private var config: RealmConfiguration
    private var backgroundThreadRealm: Realm
    private val gson = Gson()

    init {
        Realm.init(context)
        config = RealmConfiguration.Builder()
            .name(realmName)
            .allowWritesOnUiThread(true)
            .build()
        Realm.setDefaultConfiguration(config)
        backgroundThreadRealm = Realm.getInstance(config)
        println(context.filesDir)
    }


    override fun hasCards(): Boolean {
        val realmList = backgroundThreadRealm.where(CardVO::class.java)
            .findAll()
        println("Fetched object by primary key: $")
        return realmList != null
    }

    override fun getCards(): List<CardVO> {

        val returnableObject = mutableListOf<CardVO>()
        val realmList = backgroundThreadRealm.where(CardVO::class.java)
            .findAll()
        println("Fetched object by primary key: $")

        if (realmList != null) {
            returnableObject.addAll(backgroundThreadRealm.copyFromRealm(realmList))

            returnableObject.map { println("THIS IS A TRIAL" + it.name) }
        } else {
            returnableObject.addAll(
                listOf(
                    CardVO(
                        "-1", "-1", "-1", "-1", "-1", "-1",
                        "-1", "-1", "-1"
                    )
                )
            )
        }
//        println(realmList)
        return returnableObject
    }

    override fun setCards(cards: List<CardDto>) {
        val it = cards.iterator()
        while(it.hasNext()){
            backgroundThreadRealm.executeTransaction{ transactionRealm ->
                transactionRealm.copyToRealmOrUpdate(it.next().toCardVO())
            }
        }

    }

}