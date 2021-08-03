package com.davidmerino.davidmagicapp.view.activity

import android.content.Intent
import android.view.View
import android.widget.Toast
import com.davidmerino.davidmagicapp.R
import com.davidmerino.davidmagicapp.presenter.LoginPresenter
import com.davidmerino.davidmagicapp.presenter.LoginView
import kotlinx.android.synthetic.main.activity_login.*
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

class LoginActivity() : RootActivity<LoginView>(), LoginView {

    override val progress: View
        get() = TODO("Not yet implemented")

    override val presenter: LoginPresenter by instance()

    override val layoutResourceId: Int = R.layout.activity_login

    override val activityModule: Kodein.Module = Kodein.Module {
        bind<LoginPresenter>() with provider {
            LoginPresenter(
                view = this@LoginActivity,
                errorHandler = instance()
            )
        }
    }

    override fun initializeUI() {
        // Nothing to do
    }

    override fun registerListeners() {
        reset.setOnClickListener { presenter.onResetClick() }
        submit.setOnClickListener { presenter.onSubmitClick() }
    }

    override fun getUser(): String {
        return userInput.text.toString()
    }

    override fun getPassword(): String {
        return passInput.text.toString()
    }

    override fun resetFields() {
        userInput.setText("")
        passInput.setText("")
    }

    override fun navigateToMenuScreen() {
        val intent = Intent(this, MenuActivity::class.java)
        startActivity(intent)
    }

    override fun showError(string: String) {
        Toast.makeText(this, "Something went wrong: $string", Toast.LENGTH_SHORT).show()
    }
}