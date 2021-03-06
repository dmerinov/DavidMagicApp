package com.davidmerino.davidmagicapp.view.activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.davidmerino.davidmagicapp.extension.hideMe
import com.davidmerino.davidmagicapp.extension.showMe
import com.davidmerino.davidmagicapp.extension.toast
import com.davidmerino.davidmagicapp.presenter.Presenter
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.android.subKodein

/**
 * RootActivity
 */
abstract class RootActivity<out V : Presenter.View> : AppCompatActivity(), KodeinAware,
    Presenter.View {

    abstract val progress: View

    abstract val presenter: Presenter<V>

    abstract val layoutResourceId: Int

    abstract val activityModule: Kodein.Module

    override val kodein by subKodein(kodein()) {
        import(activityModule)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutResourceId)

        initializeUI()
        registerListeners()

        presenter.initialize()
    }

    override fun onResume() {
        super.onResume()
        presenter.resume()
    }

    override fun onStop() {
        super.onStop()
        presenter.stop()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.destroy()
    }

    abstract fun initializeUI()

    abstract fun registerListeners()

    override fun showError(error: String) = toast(error)

    override fun showError(errorId: Int) = toast(errorId)

    override fun showMessage(message: String) = toast(message, Toast.LENGTH_SHORT)

    override fun showMessage(messageId: Int) = toast(messageId, Toast.LENGTH_SHORT)

    override fun showProgress() = progress.showMe()

    override fun hideProgress() = progress.hideMe()

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}