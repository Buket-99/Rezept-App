package ch.teko.rezept_app.ui

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.net.URL

data class Recipe(val id: Int, val name: String, val thumbnail_url: String) {
    fun getBitmap(): Bitmap? {
        val url = URL(thumbnail_url)
        return BitmapFactory.decodeStream(url.openConnection().getInputStream())
    }
}


