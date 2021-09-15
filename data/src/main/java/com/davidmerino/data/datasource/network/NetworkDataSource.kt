package com.davidmerino.data.datasource.network

import android.util.Log
import com.davidmerino.data.api.ApiScryfallService
import com.davidmerino.data.api.ApiService
import com.davidmerino.data.api.ApiShopService
import com.davidmerino.data.mapper.toCard
import com.davidmerino.data.mapper.toLocalPrices
import com.davidmerino.data.mapper.toLocations
import com.davidmerino.domain.Either
import com.davidmerino.domain.MagicError
import com.davidmerino.domain.model.Card
import com.davidmerino.domain.model.LocalPrice
import com.davidmerino.domain.model.Shop
import io.reactivex.Single

class NetworkDataSource(
    private val apiService: ApiService,
    private val apiScryfallService: ApiScryfallService,
    private val apiShopService: ApiShopService
) : Network {

    override suspend fun getCards(): Either<MagicError, List<Card>> =
        execute {
            apiService.getAllCards().body()?.cards?.map { it.toCard() } ?: throw Exception()
        }

    override suspend fun getCardBooster(set: String): Either<MagicError, List<Card>> {
        return execute {
            apiService.mockBoosterPack(set).body()?.cards?.map { it.toCard() } ?: throw Exception()
        }
    }

    override suspend fun getCardMarketInfo(multiverseId: String): Either<MagicError, LocalPrice> {
        return execute {
            apiScryfallService.getCardInfo(multiverseId).body()?.prices?.toLocalPrices()
                ?: throw Exception()
        }
    }

    override fun getAllShops(): Single<List<Shop>> {
        return apiShopService.getAllShops().map { it.sites.map { it.toLocations() } }
    }

    suspend fun <R> execute(f: suspend () -> R): Either<MagicError, R> =
        try {
            Either.Right(f())
        } catch (t: Throwable) {
            Log.e("boosterExc", t.toString())
            Either.Left(MagicError.Default())
        }


}