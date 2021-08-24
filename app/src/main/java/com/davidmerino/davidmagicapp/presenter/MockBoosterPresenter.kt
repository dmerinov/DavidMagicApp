package com.davidmerino.davidmagicapp.presenter

import com.davidmerino.davidmagicapp.error.ErrorHandler
import com.davidmerino.davidmagicapp.mapper.toCardView
import com.davidmerino.davidmagicapp.model.CardView
import com.davidmerino.domain.interactor.usecases.GetBoosterPackUseCase


class MockBoosterPresenter(
    private val getBoosterPackUseCase: GetBoosterPackUseCase,
    errorHandler: ErrorHandler, view: MockBoosterCardView
) :
    Presenter<MockBoosterCardView>(errorHandler, view) {

    override fun initialize() {
        //nothing to do
    }

    override fun resume() {
        //nothing to do
    }

    override fun stop() {
        //nothing to do
    }

    override fun destroy() {
        //nothing to do
    }

    private fun getBoosterPack(expansion: String) {
        getBoosterPackUseCase.execute(
            expansion = expansion,
            onSuccess = { view.showBoosterPack(it.map { it.toCardView() }) },
            onError = onError { view.showError(it) }
        )
    }

    fun searchCard() {
        getBoosterPack(view.getCardExpansion())
    }
}

interface MockBoosterCardView : Presenter.View {
    fun getCardExpansion(): String
    fun showBoosterPack(boosterPack: List<CardView>)
}