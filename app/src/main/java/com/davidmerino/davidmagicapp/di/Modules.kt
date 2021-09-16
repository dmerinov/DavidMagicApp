package com.davidmerino.davidmagicapp.di

import android.content.Context
import com.davidmerino.data.api.ApiScryfallService
import com.davidmerino.data.api.ApiService
import com.davidmerino.data.api.ApiShopService
import com.davidmerino.data.api.createService
import com.davidmerino.data.datasource.CommonRepository
import com.davidmerino.data.datasource.cache.CommonCache
import com.davidmerino.data.datasource.local.Local
import com.davidmerino.data.datasource.local.RealmDatabase
import com.davidmerino.data.datasource.network.Network
import com.davidmerino.data.datasource.network.NetworkDataSource
import com.davidmerino.data.datasource.preferences.CommonPreferences
import com.davidmerino.data.datasource.preferences.Preferences
import com.davidmerino.davidmagicapp.error.AndroidErrorHandler
import com.davidmerino.davidmagicapp.error.ErrorHandler
import com.davidmerino.davidmagicapp.executor.RxExecutor
import com.davidmerino.domain.executor.Executor
import com.davidmerino.domain.interactor.usecases.GetLifeCounter
import com.davidmerino.domain.interactor.usecases.SetLifeCounter
import com.davidmerino.domain.repository.Repository
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

/**
 * Modules
 */
fun appModule(context: Context) = Kodein.Module("appModule") {
    bind<Context>() with singleton { context }
    bind<Executor>() with singleton { RxExecutor() }
    bind<ErrorHandler>() with singleton { AndroidErrorHandler(context = context) }
}

val domainModule = Kodein.Module("domainModule") {
    bind() from singleton { SetLifeCounter(repository = instance(), executor = instance()) }
    bind() from singleton { GetLifeCounter(repository = instance(), executor = instance()) }
}

val dataModule = Kodein.Module("dataModule") {
    bind<Repository>() with singleton {
        CommonRepository(
            network = instance(),
            local = instance(),
            preferences = instance(),
            cache = instance()
        )
    }
    bind<Network>() with singleton {
        NetworkDataSource(
            apiService = instance(),
            apiScryfallService = instance(),
            apiShopService = instance()
        )
    }
    bind<ApiService>() with singleton {
        createService(endPoint = ApiService.END_POINT)
    }
    bind<ApiScryfallService>() with singleton {
        createService(endPoint = ApiScryfallService.END_POINT)
    }
    bind<ApiShopService>() with singleton {
        createService(endPoint = ApiShopService.END_POINT)
    }
    bind<Local>() with singleton { RealmDatabase(context = instance()) }
    bind<CommonCache>() with singleton { CommonCache() }
    bind<Preferences>() with singleton { CommonPreferences(context = instance()) }
}