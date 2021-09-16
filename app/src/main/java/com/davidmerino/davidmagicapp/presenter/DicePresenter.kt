package com.davidmerino.davidmagicapp.presenter

import com.davidmerino.davidmagicapp.error.ErrorHandler
import com.davidmerino.domain.executor.Executor
import com.davidmerino.domain.interactor.usecases.GetLifeCounter
import com.davidmerino.domain.interactor.usecases.SetLifeCounter

class DicePresenter(
    private val setLifeCounter: SetLifeCounter,
    private val getLifeCounter: GetLifeCounter,
    errorHandler: ErrorHandler,
    executor: Executor,
    view: DiceView,
) : Presenter<DiceView>(executor, errorHandler, view) {

    companion object {
        private const val DEFAULT_LIFE = 20
    }

    private var player1Life = 0
    private var player2Life = 0

    override fun initialize() {

        if (player1Life == 0) {
            player1Life = DEFAULT_LIFE
            setLifeCounter.execute(Player.PLAYER_1.ordinal, DEFAULT_LIFE)
            view.showLife(player1Life.toString(), Player.PLAYER_1)
        }

        getLifeCounter.execute(
            onSuccess = {
                player2Life = it
            },
            onError = onError { },
            Player.PLAYER_2.ordinal
        )

        if (player2Life == 0) {
            player2Life = DEFAULT_LIFE
            setLifeCounter.execute(Player.PLAYER_2.ordinal, DEFAULT_LIFE)
            view.showLife(player2Life.toString(), Player.PLAYER_2)
        }
    }

    override fun resume() {
        getLifeCounter.execute(
            onSuccess = {
                player1Life = it
                view.showLife(player1Life.toString(), Player.PLAYER_1)
            },
            onError = onError { },
            Player.PLAYER_1.ordinal
        )

        getLifeCounter.execute(
            onSuccess = {
                player2Life = it
                view.showLife(player2Life.toString(), Player.PLAYER_2)
            },
            onError = onError { },
            Player.PLAYER_2.ordinal
        )
    }

    override fun stop() {
        if (view.isLocked()) {

            getLifeCounter.execute(
                onSuccess = {
                    setLifeCounter.execute(Player.PLAYER_1.ordinal, it + 10)
                },
                onError = onError { },
                Player.PLAYER_1.ordinal
            )

            getLifeCounter.execute(
                onSuccess = {
                    setLifeCounter.execute(Player.PLAYER_2.ordinal, it + 10)
                },
                onError = onError { it },
                Player.PLAYER_2.ordinal
            )
        }
    }

    override fun destroy() {
        // nothing to do
    }

    fun incrementCounter(player: Player) {
        when (player) {
            Player.PLAYER_1 -> {
                getLifeCounter.execute(
                    onSuccess = {
                        player1Life += 1
                        setLifeCounter.execute(Player.PLAYER_1.ordinal, player1Life)
                        view.showLife(player1Life.toString(), player)
                    },
                    onError = onError { it },
                    Player.PLAYER_1.ordinal
                )

            }
            Player.PLAYER_2 -> {
                getLifeCounter.execute(
                    onSuccess = {
                        player2Life += 1
                        setLifeCounter.execute(Player.PLAYER_2.ordinal, player2Life)
                        view.showLife(player2Life.toString(), player)
                    },
                    onError = onError { it },
                    Player.PLAYER_2.ordinal
                )
            }
        }
    }

    fun resetCounters() {
        player1Life = DEFAULT_LIFE
        player2Life = DEFAULT_LIFE
        setLifeCounter.execute(Player.PLAYER_1.ordinal, DEFAULT_LIFE)
        setLifeCounter.execute(Player.PLAYER_2.ordinal, DEFAULT_LIFE)

        getLifeCounter.execute(
            onSuccess = {
                player1Life = it
                view.showLife(player1Life.toString(), Player.PLAYER_1)
            },
            onError = onError { it },
            Player.PLAYER_1.ordinal
        )

        getLifeCounter.execute(
            onSuccess = {
                player2Life = it
                view.showLife(player2Life.toString(), Player.PLAYER_2)
            },
            onError = onError { it },
            Player.PLAYER_2.ordinal
        )

    }

    fun decrementCounter(player: Player) {
        when (player) {
            Player.PLAYER_1 -> {
                getLifeCounter.execute(
                    onSuccess = {
                        player1Life -= 1
                        setLifeCounter.execute(Player.PLAYER_1.ordinal, player1Life)
                        view.showLife(player1Life.toString(), player)
                    },
                    onError = onError { it },
                    Player.PLAYER_1.ordinal
                )
            }
            Player.PLAYER_2 -> {
                getLifeCounter.execute(
                    onSuccess = {
                        player2Life -= 1
                        setLifeCounter.execute(Player.PLAYER_2.ordinal, player2Life)
                        view.showLife(player2Life.toString(), player)
                    },
                    onError = onError { it },
                    Player.PLAYER_2.ordinal
                )
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