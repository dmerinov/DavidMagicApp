package com.davidmerino.davidmagicapp.presenter

import com.davidmerino.data.datasource.cache.Cache
import com.davidmerino.davidmagicapp.error.ErrorHandler

class DicePresenter(
    private val cache: Cache, //cambiar de sitio al repo.
    errorHandler: ErrorHandler,
    view: DiceView,
) : Presenter<DiceView>(errorHandler, view) {

    companion object {
        private const val DEFAULT_LIFE = 20
    }

    private var player1Life = 0
    private var player2Life = 0

    override fun initialize() {
        if (cache.getLife(Player.PLAYER_1.ordinal) == 0) {
            cache.setLife(Player.PLAYER_1.ordinal, DEFAULT_LIFE)
        }
        if (cache.getLife(Player.PLAYER_2.ordinal) == 0) {
            cache.setLife(Player.PLAYER_2.ordinal, DEFAULT_LIFE)
        }
    }

    override fun resume() {
        player1Life = cache.getLife(Player.PLAYER_1.ordinal)
        player2Life = cache.getLife(Player.PLAYER_2.ordinal)
        view.showLife(player1Life.toString(), Player.PLAYER_1)
        view.showLife(player2Life.toString(), Player.PLAYER_2)
    }

    override fun stop() {
        if (view.isLocked()) {
            player1Life = cache.getLife(Player.PLAYER_1.ordinal)
            player2Life = cache.getLife(Player.PLAYER_2.ordinal)
            cache.setLife(Player.PLAYER_1.ordinal, player1Life + 10)
            cache.setLife(Player.PLAYER_2.ordinal, player2Life + 10)
        }
    }

    override fun destroy() {
        // nothing to do
    }

    fun incrementCounter(player: Player) {
        when (player) {
            Player.PLAYER_1 -> {
                player1Life = cache.getLife(Player.PLAYER_1.ordinal) + 1
                cache.setLife(Player.PLAYER_1.ordinal, player1Life)
                view.showLife(player1Life.toString(), player)
            }
            Player.PLAYER_2 -> {
                player2Life = cache.getLife(Player.PLAYER_2.ordinal) + 1
                cache.setLife(Player.PLAYER_2.ordinal, player2Life)
                view.showLife(player2Life.toString(), player)
            }
        }
    }

    fun resetCounters() {
        cache.setLife(Player.PLAYER_1.ordinal, DEFAULT_LIFE)
        cache.setLife(Player.PLAYER_2.ordinal, DEFAULT_LIFE)
        view.showLife(cache.getLife(Player.PLAYER_1.ordinal).toString(), Player.PLAYER_1)
        view.showLife(cache.getLife(Player.PLAYER_2.ordinal).toString(), Player.PLAYER_2)
    }

    fun decrementCounter(player: Player) {
        when (player) {
            Player.PLAYER_1 -> {
                player1Life = cache.getLife(Player.PLAYER_1.ordinal) - 1
                cache.setLife(Player.PLAYER_1.ordinal, player1Life)
                view.showLife(player1Life.toString(), player)
            }
            Player.PLAYER_2 -> {
                player2Life = cache.getLife(Player.PLAYER_2.ordinal) - 1
                cache.setLife(Player.PLAYER_2.ordinal, player2Life)
                view.showLife(player2Life.toString(), player)
            }
        }
    }
}

interface DiceView : Presenter.View {
    fun showLife(life: String, player: Player)
    fun isLocked(): Boolean
}

enum class Player {
    PLAYER_1,
    PLAYER_2
}