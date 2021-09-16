package com.davidmerino.davidmagicapp.presenter

import com.davidmerino.davidmagicapp.error.ErrorHandler
import com.davidmerino.domain.executor.Executor
import com.davidmerino.domain.repository.Repository

class DicePresenter(
    private val repository: Repository,
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

        repository.getLifeCounter(Player.PLAYER_1.ordinal).fold(
            error = {},
            success = { player1Life = it }
        )

        if (player1Life == 0) {
            player1Life = DEFAULT_LIFE
            repository.setLifeCounter(Player.PLAYER_1.ordinal, DEFAULT_LIFE)
            view.showLife(player1Life.toString(), Player.PLAYER_1)
        }

        repository.getLifeCounter(Player.PLAYER_2.ordinal).fold(
            error = {},
            success = { player2Life = it }
        )

        if (player2Life == 0) {
            player2Life = DEFAULT_LIFE
            repository.setLifeCounter(Player.PLAYER_2.ordinal, DEFAULT_LIFE)
            view.showLife(player2Life.toString(), Player.PLAYER_2)
        }
    }

    override fun resume() {
        repository.getLifeCounter(Player.PLAYER_1.ordinal).fold(
            error = {},
            success = {
                player1Life = it
                view.showLife(player1Life.toString(), Player.PLAYER_1)
            }
        )

        repository.getLifeCounter(Player.PLAYER_2.ordinal).fold(
            error = {},
            success = {
                player2Life = it
                view.showLife(player2Life.toString(), Player.PLAYER_2)
            }
        )
    }

    override fun stop() {
        if (view.isLocked()) {

            repository.getLifeCounter(Player.PLAYER_1.ordinal).fold(
                error = {},
                success = {
                    repository.setLifeCounter(Player.PLAYER_1.ordinal, player1Life + 10)
                }
            )

            repository.getLifeCounter(Player.PLAYER_2.ordinal).fold(
                error = {},
                success = {
                    repository.setLifeCounter(Player.PLAYER_2.ordinal, player2Life + 10)
                }
            )
        }
    }

    override fun destroy() {
        // nothing to do
    }

    fun incrementCounter(player: Player) {
        when (player) {
            Player.PLAYER_1 -> {
                repository.getLifeCounter(Player.PLAYER_1.ordinal).fold(
                    error = {},
                    success = {
                        player1Life = it + 1
                        repository.setLifeCounter(Player.PLAYER_1.ordinal, player1Life)
                        view.showLife(player1Life.toString(), player)
                    }
                )

            }
            Player.PLAYER_2 -> {
                repository.getLifeCounter(Player.PLAYER_2.ordinal).fold(
                    error = {},
                    success = {
                        player2Life = it + 1
                        repository.setLifeCounter(Player.PLAYER_2.ordinal, player2Life)
                        view.showLife(player2Life.toString(), player)
                    }
                )
            }
        }
    }

    fun resetCounters() {
        player1Life = DEFAULT_LIFE
        player2Life = DEFAULT_LIFE
        repository.setLifeCounter(Player.PLAYER_1.ordinal, DEFAULT_LIFE)
        repository.setLifeCounter(Player.PLAYER_2.ordinal, DEFAULT_LIFE)

        repository.getLifeCounter(Player.PLAYER_1.ordinal).fold(
            error = {},
            success = {
                view.showLife(player1Life.toString(), Player.PLAYER_1)
            }
        )

        repository.getLifeCounter(Player.PLAYER_2.ordinal).fold(
            error = {},
            success = {
                view.showLife(player2Life.toString(), Player.PLAYER_2)
            }
        )

    }

    fun decrementCounter(player: Player) {
        when (player) {
            Player.PLAYER_1 -> {
                repository.getLifeCounter(Player.PLAYER_1.ordinal).fold(
                    error = {},
                    success = {
                        player1Life = it - 1
                        repository.setLifeCounter(Player.PLAYER_1.ordinal, player1Life)
                        view.showLife(player1Life.toString(), player)
                    }
                )

            }
            Player.PLAYER_2 -> {
                repository.getLifeCounter(Player.PLAYER_2.ordinal).fold(
                    error = {},
                    success = {
                        player2Life = it - 1
                        repository.setLifeCounter(Player.PLAYER_2.ordinal, player2Life)
                        view.showLife(player2Life.toString(), player)
                    }
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