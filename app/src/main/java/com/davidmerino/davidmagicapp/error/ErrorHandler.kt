package com.davidmerino.davidmagicapp.error

/**
 * ErrorHandler.
 */
interface ErrorHandler {
    fun convert(e: Exception): String
}