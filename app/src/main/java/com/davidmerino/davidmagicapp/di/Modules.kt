package com.davidmerino.davidmagicapp.di

import android.content.Context
import com.davidmerino.data.datasource.CommonRepository
import com.davidmerino.data.datasource.cache.Cache
import com.davidmerino.data.datasource.local.Local
import com.davidmerino.data.datasource.local.RealmDatabase
import com.davidmerino.data.datasource.network.Network
import com.davidmerino.data.datasource.network.NetworkDataSource
import com.davidmerino.davidmagicapp.error.AndroidErrorHandler
import com.davidmerino.davidmagicapp.error.ErrorHandler
import com.davidmerino.davidmagicapp.executor.RxExecutor
import com.davidmerino.domain.executor.Executor
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
    // Add here data dependencies
}

val dataModule = Kodein.Module("dataModule") {
    bind<Repository>() with singleton {
        CommonRepository(
            network = instance(),
            local = instance()
        )
    }
    bind<Network>() with singleton { NetworkDataSource() }
    bind<Local>() with singleton { RealmDatabase(context = instance()) }
    bind<Cache>() with singleton {Cache()}
}