package com.davidmerino.davidmagicapp.presenter

import com.davidmerino.davidmagicapp.error.ErrorHandler
import com.davidmerino.davidmagicapp.mapper.toCardView
import com.davidmerino.davidmagicapp.model.CardView
import com.davidmerino.domain.repository.Repository


class MockBoosterPresenter(
    private val repository: Repository,
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
        repository.getBoosterPack(
            expansion,
            success = { view.showBoosterPack(it.map { it.toCardView() }) },
            error = { error("could not load booster pack, check if your expansion input is correct") }
        )
    }

    fun searchCard() {
        getBoosterPack(view.getCardName())
    }
}

interface MockBoosterCardView : Presenter.View {
    fun getCardName(): String
    fun showBoosterPack(boosterPack: List<CardView>)
}