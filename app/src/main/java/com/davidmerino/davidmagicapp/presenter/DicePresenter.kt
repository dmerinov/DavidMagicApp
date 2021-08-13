package com.davidmerino.davidmagicapp.presenter

import android.app.KeyguardManager
import android.content.Context
import com.davidmerino.davidmagicapp.error.ErrorHandler

class DicePresenter(
    errorHandler: ErrorHandler,
    view: DiceView,
    context: Context
) : Presenter<DiceView>(errorHandler, view) {

    companion object {
        private const val DEFAULT_LIFE = 20
        private const val ONLOCKTOAST = "you life total has been incremented by 10"
    }


    private var player1Life = DEFAULT_LIFE
    private var player2Life = DEFAULT_LIFE
    private val keyguardManager: KeyguardManager? =
        context.getSystemService(KeyguardManager::class.java) as? KeyguardManager

    override fun initialize() {
        view.showLife(player1Life.toString(), Player.PLAYER_1)
        view.showLife(player2Life.toString(), Player.PLAYER_2)

    }

    override fun resume() {
    }

    override fun stop() {
        if (keyguardManager != null) {
            if (!keyguardManager.isKeyguardLocked) {
                player1Life += 10
                player2Life += 10
                view.showLife(player1Life.toString(), Player.PLAYER_1)
                view.showLife(player2Life.toString(), Player.PLAYER_2)
            }
        }
    }

    override fun destroy() {
        //nothing to do
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