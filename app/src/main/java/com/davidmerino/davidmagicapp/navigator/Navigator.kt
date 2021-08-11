package com.davidmerino.davidmagicapp.navigator

import android.content.Context
import com.davidmerino.davidmagicapp.view.activity.DetailCardActivity
import com.davidmerino.davidmagicapp.view.activity.MenuActivity

/**
 * Navigator.
 */
fun navigateToDetailCardActivity(context: Context, id: String) {
    context.startActivity(DetailCardActivity.getCallingIntent(context, id))
}
fun navigateToMenuActivity(context: Context) {
    context.startActivity(MenuActivity.getCallingIntent2(context))
}