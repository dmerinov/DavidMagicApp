package com.davidmerino.data.datasource

import com.davidmerino.domain.Either
import com.davidmerino.domain.MagicError

suspend fun <R> execute(f: suspend () -> R): Either<MagicError, R> =
    try {
        Either.Right(f())
    } catch (t: Throwable) {
        Either.Left(MagicError.Default())
    }