package com.davidmerino.davidmagicapp.presenter

import com.davidmerino.data.datasource.cache.ICache
import com.davidmerino.davidmagicapp.error.ErrorHandler

class DicePresenter(

    private val cache: ICache,
    errorHandler: ErrorHandler,
    view: DiceView,
) : Presenter<DiceView>(errorHandler, view) {

    private var player1Life = cache.getLife(Player.PLAYER_1.ordinal)
    private var player2Life = cache.getLife(Player.PLAYER_2.ordinal)

    override fun initialize() {
    }

    override fun resume() {
        player1Life = cache.getLife(Player.PLAYER_1.ordinal)
        player2Life = cache.getLife(Player.PLAYER_2.ordinal)
        view.showLife(player1Life.toString(), Player.PLAYER_1)
        view.showLife(player2Life.toString(), Player.PLAYER_2)
    }

    override fun stop() {
        player1Life += 10
        player2Life += 10
        cache.setLife(Player.PLAYER_1.ordinal, player1Life)
        cache.setLife(Player.PLAYER_2.ordinal, player2Life)
    }

    override fun destroy() {
        cache.setLife(Player.PLAYER_1.ordinal, player1Life)
        cache.setLife(Player.PLAYER_2.ordinal, player2Life)
    }

    fun incrementCounter(player: Player) {
        when (player) {
            Player.PLAYER_1 -> {
                player1Life++
                view.showLife(player1Life.toString(), player)
            }
            Player.PLAYER_2 -> {
                player2Life++
                view.showLife(player2Life.toString(), player)
            }
        }
    }

    fun decrementCounter(player: Player) {
        when (player) {
            Player.PLAYER_1 -> {
                player1Life--
                view.showLife(player1Life.toString(), player)
            }
            Player.PLAYER_2 -> {
                player2Life--
                view.showLife(player2Life.toString(), player)
            }
        }
    }
}

interface DiceView : Presenter.View {
    fun showLife(life: String, player: Player)
}

enum class Player {
    PLAYER_1,
    PLAYER_2
}