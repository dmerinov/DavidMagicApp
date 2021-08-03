package com.davidmerino.davidmagicapp.presenter

import com.davidmerino.davidmagicapp.error.ErrorHandler

class LoginPresenter(errorHandler: ErrorHandler, view: LoginView) :
    Presenter<LoginView>(errorHandler, view) {
    override fun initialize() {

    }

    override fun resume() {
    }

    override fun stop() {
    }

    override fun destroy() {
    }

    fun onResetClick() {
        view.resetFields()
    }

    fun onSubmitClick() {
        if (validateUser(view.getUser(), view.getPassword()))
            view.navigateToMenuScreen()
        else
            view.showError("Eeither user or password are wrong")
    }

    private fun validateUser(user: String, password: String): Boolean {
        return user.equals("usuario") && password.equals("password")
    }

}

interface LoginView : Presenter.View {
    fun getUser(): String
    fun getPassword(): String
    fun resetFields()
    fun navigateToMenuScreen()
}