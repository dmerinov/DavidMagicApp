package com.davidmerino.davidmagicapp.presenter

import com.davidmerino.davidmagicapp.error.ErrorHandler
import com.davidmerino.domain.Either
import com.davidmerino.domain.MagicError
import com.davidmerino.domain.executor.Executor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.withContext

/**
 * Presenter
 */
abstract class Presenter<out V : Presenter.View>(
    protected val executor: Executor,
    protected val errorHandler: ErrorHandler,
    val view: V
) {

    protected val job = SupervisorJob()
    protected val scope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    abstract fun initialize()

    abstract fun resume()

    abstract fun stop()

    abstract fun destroy()

    protected fun onError(callback: (String) -> Unit): (Throwable) -> Unit = {
        view.hideProgress()

        val message = errorHandler.convert(it as Exception)

//        Crashlytics.logException(it)
        callback(message)
    }

    protected suspend fun <T> execute(f: suspend () -> Either<MagicError, T>): Either<MagicError, T> =
        withContext(Dispatchers.IO) { f() }

    interface View {
        fun showProgress()

        fun hideProgress()

        fun showError(error: String)

        fun showError(errorId: Int)

        fun showMessage(message: String)

        fun showMessage(messageId: Int)
    }
}