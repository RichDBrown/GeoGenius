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
 * Converts a drawable resource into a [Bitmap].
 *
 * The function performs the following steps:
 * 1. Retrieves the drawable resource using the provided [drawableId].
 * 2. Creates a new [Bitmap] with dimensions of 25x25 pixels.
 * 3. Initializes a [Canvas] with the created bitmap.
 * 4. Sets the bounds of the drawable to match the size of the canvas.
 * 5. Draws the drawable onto the canvas, which in turn draws it onto the bitmap.
 *
 * @param context The context used to retrieve the drawable resource.
 * @param drawableId The resource ID of the drawable to be converted. This must be a valid drawable resource ID.
 *
 * @return A [Bitmap] representation of the drawable. The bitmap is created with a fixed size of 25x25 pixels and
 *         uses the [Bitmap.Config.ARGB_8888] configuration.
 */
fun drawableToBitmap(context: Context, @DrawableRes drawableId: Int): Bitmap {
    val drawable: Drawable = context.getDrawable(drawableId)!!
    val bitmap = Bitmap.createBitmap(
        //Needed to prevent exception
        25,
        25,
        Bitmap.Config.ARGB_8888
    )
    val canvas = Canvas(bitmap)
    drawable.setBounds(0, 0, canvas.width, canvas.height)
    drawable.draw(canvas)
    return bitmap
}

/**
 * Composable function to display a background image using a [Bitmap].
 *
 * This function converts the provided [Bitmap] into an [ImageBitmap] and then uses the
 * [Image] composable to display it. The image is displayed with the following properties:
 * - `contentDescription`: Set to "App Background" for accessibility purposes.
 * - `modifier`: Applies the [Modifier.fillMaxSize()] to make the image cover the entire available space.
 * - `contentScale`: Uses [ContentScale.Crop] to scale the image to fill the space, cropping if necessary.
 *
 * @param drawableToBitmap The [Bitmap] to be displayed as the background image.
 *
 */
@Composable
fun DisplayBackground(drawableToBitmap: Bitmap) {
    val imageBitmap: ImageBitmap = drawableToBitmap.asImageBitmap()

    Image(
        bitmap = imageBitmap,
        contentDescription = "App Background",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
    )
}