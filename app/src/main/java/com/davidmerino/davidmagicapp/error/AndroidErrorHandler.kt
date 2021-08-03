package com.davidmerino.davidmagicapp.error

import android.content.Context
import com.davidmerino.davidmagicapp.R

/**
 * AndroidErrorHandler.
 */
class AndroidErrorHandler(val context: Context) : ErrorHandler {
    override fun convert(e: Exception): String =
            when (e) {
                else -> context.getString(R.string.default_error)
            }

}