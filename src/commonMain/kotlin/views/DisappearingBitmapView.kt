package views

import com.soywiz.korge.view.View
import com.soywiz.korge.view.image
import com.soywiz.korim.bitmap.Bitmap32
import com.soywiz.korim.color.Colors
import com.soywiz.korim.color.RGBA

class DisappearingBitmapView(val bitmap: Bitmap32) : DisappearingView() {
    override suspend fun initGraphics() = image(bitmap)
    override suspend fun initContours(graphics: View) = image(bitmap.edges())
}

fun Bitmap32.edges(): Bitmap32 {
    val img = copyTo(Bitmap32(width, height))
    img.toMonochromeInPlace()

    val scaleFactor = 0.8
    val blur = img.scale(scaleFactor, smooth = true).scale(1/scaleFactor, smooth = false)
    blur.toMonochromeInPlace()

    // Images may have slightly different size depending on [scaleFactor]
    val (smaller, bigger) = if(img.width <= blur.width) img to blur else blur to img

    val edges = Bitmap32.diff(smaller, bigger.copySliceWithSize(0, 0, smaller.width, smaller.height))
    return edges
}

fun Bitmap32.scale(scale: Double, smooth: Boolean)
    = scaled((width * scale).toInt(), (height * scale).toInt(), smooth = smooth)

fun Bitmap32.toMonochromeInPlace(background: RGBA = Colors.TRANSPARENT_WHITE, foreground: RGBA = Colors.BLACK) {
    updateColors { color -> if(color.a == 0) background else foreground }
}