package com.app.architectureapp.utils

import android.content.Context
import android.content.res.Resources
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.text.Html
import android.text.Spanned
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.app.architectureapp.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.google.android.material.snackbar.Snackbar

fun String.toHtml(text: String): Spanned? {
    return Html.fromHtml("<b><font color='#006A69'>$text</font></b> $this", Html.FROM_HTML_MODE_COMPACT)
}

fun View.showSnackBar(message: String, action: (Snackbar.() -> Unit)? = null) {
    val snackBar = Snackbar.make(this, message, Snackbar.LENGTH_SHORT)
        .setTextColor(ContextCompat.getColor(context, R.color.white))
    action?.let { snackBar.it() }
    snackBar.show()
}

fun Snackbar.action(message: String, action: (View) -> Unit) {
    this.setAction(message, action)
        .setActionTextColor(ContextCompat.getColor(context, R.color.white))
}

fun ImageView.loadImage(image: String) {
    Glide.with(this).load(image).transition(DrawableTransitionOptions.withCrossFade()).into(this)
}

fun Context.isInternetAvailable(): Boolean {
    val connectivityManager =
        getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
    if (connectivityManager != null) {
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                Log.e("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                Log.e("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                Log.e("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                return true
            }
        }
    }
    return false
}

fun Context.convertDpToPixel(dp: Float): Int {
    val resources: Resources = resources
    val metrics: DisplayMetrics = resources.displayMetrics
    return (dp * (metrics.densityDpi / 160f)).toInt()
}