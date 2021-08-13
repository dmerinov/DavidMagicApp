package com.davidmerino.davidmagicapp.navigator

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.davidmerino.davidmagicapp.view.activity.*

/**
 * Navigator.
 */
fun navigateToDetailCardActivity(context: Context, id: String) {
    context.startActivity(DetailCardActivity.getCallingIntent(context, id))
}

fun navigateToMenuActivity(context: Context) {
    context.startActivity(MenuActivity.getCallingIntent2(context))
}

fun openExternalUrl(context: Context, url: String) {
    val intent = Intent(Intent.ACTION_VIEW).apply {
        data = Uri.parse(url)
    }
    context.startActivity(intent)
}

fun navigateToDiceActivity(context: Context) {
    context.startActivity(DiceActivity.getCallingIntent(context))
}

fun navigateToCardListActivity(context: Context) {
    context.startActivity(CardListActivity.getCallingIntent(context))
}

fun navigateToSearchCardActivity(context: Context) {
    context.startActivity(SearchCardActivity.getCallingIntent(context))
}