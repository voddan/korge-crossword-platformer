package views

import com.soywiz.korge.view.Container
import com.soywiz.korge.view.graphics
import com.soywiz.korim.color.Colors
import com.soywiz.korma.geom.Rectangle
import com.soywiz.korma.geom.vector.rect

class Platform(rect: Rectangle) : Container() {
    init {
        graphics {
            fill(Colors.GREEN) {
                rect(rect)
            }
        }
    }
}