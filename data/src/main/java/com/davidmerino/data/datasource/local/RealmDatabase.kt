package com.davidmerino.data.datasource.local

import android.content.Context
import com.davidmerino.data.mapper.toCard
import com.davidmerino.data.mapper.toCardVO
import com.davidmerino.data.model.cardVO.CardVO
import com.davidmerino.domain.model.Card
import io.realm.Realm
import io.realm.RealmConfiguration

class RealmDatabase(context: Context) : Local {

    private val realmName: String = "Database"
    private var config: RealmConfiguration
    private var backgroundThreadRealm: Realm

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
        return realmList != null
    }

    override fun getCards(): List<Card> =
        backgroundThreadRealm.where(CardVO::class.java)
            .findAll().map {
                it.toCard()
            }

    override fun setCards(cards: List<Card>) {
        val it = cards.map { it.toCardVO() }.iterator()
        while (it.hasNext()) {
            backgroundThreadRealm.executeTransaction { transactionRealm ->
                transactionRealm.copyToRealmOrUpdate(it.next())
            }
        }

    }

    override fun getCardByID(id: String): Card {
        val fetchedCard = backgroundThreadRealm.where(CardVO::class.java)
            .equalTo("id", id).findFirst()
        var returnCard = CardVO()
        if (fetchedCard != null) {
            returnCard = backgroundThreadRealm.copyFromRealm(fetchedCard)
        }
        return returnCard.toCard()
    }

}