package com.example.geogenius.views

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import com.example.geogenius.R

/**
 * Converts drawable resource into bitmap
 *
 *
 *
 * @param context The context from which to retrieve the drawable resource.
 * @param drawableId The resource ID of the drawable to convert.
 * @return A bitmap of drawable resource.
 */
private fun drawableToBitmap(context: Context, @DrawableRes drawableId: Int): Bitmap {
    val drawable: Drawable = context.getDrawable(drawableId)!!
    val bitmap = Bitmap.createBitmap(
        25,
        25,
        Bitmap.Config.ARGB_8888
    )
    val canvas = Canvas(bitmap)
    drawable.setBounds(0, 0, canvas.width, canvas.height)
    drawable.draw(canvas)
    return bitmap
}

@Composable
fun DisplayBackground(context: Context) {
    val bitmap = drawableToBitmap(context, R.drawable.background_color)
    val imageBitmap: ImageBitmap = bitmap.asImageBitmap()

    Image(
        bitmap = imageBitmap,
        contentDescription = "App Background",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
    )
}