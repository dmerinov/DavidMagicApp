package com.davidmerino.data.datasource.local

import com.davidmerino.data.mapper.toCard
import com.davidmerino.data.mapper.toCardVO
import com.davidmerino.data.model.cardVO.CardVO
import com.davidmerino.domain.Either
import com.davidmerino.domain.MagicError
import com.davidmerino.domain.model.Card
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.kotlin.executeTransactionAwait
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RealmDatabase() : Local {

    private val realmName: String = "Database"
    private var config: RealmConfiguration
    private var backgroundThreadRealm: Realm

    init {
        config = RealmConfiguration.Builder()
            .name(realmName)
            .allowWritesOnUiThread(true)
            .build()
        Realm.setDefaultConfiguration(config)
        backgroundThreadRealm = Realm.getInstance(config)
    }


    override fun hasCards(): Boolean {
        val realmList = backgroundThreadRealm.where(CardVO::class.java)
            .findAll()
        return realmList != null
    }

    override suspend fun getCards(): List<Card> {

        val cardList: List<Card>

        withContext(Dispatchers.IO) {
            cardList = backgroundThreadRealm.where(CardVO::class.java)
                .findAll().map {
                    it.toCard()
                }
        }
        return cardList
    }


    override suspend fun setCards(cards: List<Card>) {
        val it = cards.map { it.toCardVO() }.iterator()
        withContext(Dispatchers.IO) {
            while (it.hasNext()) {
                backgroundThreadRealm.executeTransactionAwait { transactionRealm ->
                    transactionRealm.copyToRealmOrUpdate(it.next())
                }
            }
        }
    }

    override fun getCardByID(id: String): Either<MagicError, Card> {
        val fetchedCard = backgroundThreadRealm.where(CardVO::class.java)
            .equalTo("id", id).findFirst()
        var returnCard = CardVO()
        if (fetchedCard != null) {
            returnCard = backgroundThreadRealm.copyFromRealm(fetchedCard)
        }
        return Either.Right(returnCard.toCard())
    }

}