package com.davidmerino.davidmagicapp.view.app

import android.app.Application
import com.davidmerino.davidmagicapp.di.appModule
import com.davidmerino.davidmagicapp.di.dataModule
import com.davidmerino.davidmagicapp.di.domainModule
import io.realm.Realm
import kotlinx.coroutines.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware

/**
 * App.
 */
class App : Application(), KodeinAware {

    override val kodein by Kodein.lazy {
        import(appModule(this@App))
        import(domainModule)
        import(dataModule)
    }

    override fun onCreate() {
        super.onCreate()
        CoroutineScope(Dispatchers.Main + SupervisorJob()).launch {
            withContext(Dispatchers.IO) {
                Realm.init(this@App)
            }
        }
    }

}