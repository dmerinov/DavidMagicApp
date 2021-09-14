package com.davidmerino.domain

sealed class MagicError {
    object NoInternet : MagicError()
    data class Default(val message: String = "") : MagicError()
    data class DatabaseAccess(val throwable: Throwable) : MagicError()
    object NotFound : MagicError()
}

enum class UnauthorizedErrorType {
    NO_ACTIVE, GIBRALTAR, COMPANY
}

object Success
