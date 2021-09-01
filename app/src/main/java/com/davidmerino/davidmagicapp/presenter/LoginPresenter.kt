package com.davidmerino.davidmagicapp.presenter

import com.davidmerino.davidmagicapp.error.ErrorHandler

class LoginPresenter(errorHandler: ErrorHandler, view: LoginView) :
    Presenter<LoginView>(errorHandler, view) {

    override fun initialize() {
        // nothing to do
    }

    override fun resume() {
        // nothing to do
    }

    override fun stop() {
        // nothing to do
    }

    override fun destroy() {
        // nothing to do
    }

    fun onResetClick() {
        view.resetFields()
    }

    fun onSubmitClick() {
        if (validateUser(view.getUser(), view.getPassword()))
            view.navigateToMenuScreen()
        else
            view.showError("Either user or password are wrong")
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