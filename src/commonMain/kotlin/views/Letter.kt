package views

import com.soywiz.korge.view.Container
import com.soywiz.korge.view.graphics
import com.soywiz.korge.view.solidRect
import com.soywiz.korim.color.Colors

class Letter : Container() {
    val boxSize = 30

    init {
        graphics {
            solidRect(boxSize, boxSize, color = Colors.LIGHTYELLOW)
        }
    }
}