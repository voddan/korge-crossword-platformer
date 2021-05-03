package views

import com.soywiz.korge.view.Container
import com.soywiz.korge.view.graphics
import com.soywiz.korge.view.solidRect
import com.soywiz.korge.view.text
import com.soywiz.korim.color.Colors
import com.soywiz.korma.geom.Point
import com.soywiz.korma.geom.vector.circle

class Letter(val value: Char) : Container() {
    companion object const {
        const val SIZE = 20.0
    }

    init {
        graphics {
            solidRect(SIZE, SIZE, color = Colors.LIGHTYELLOW)
            text("$value", color = Colors["#c22aff"], textSize = SIZE) {
                pos = Point(0.2, -0.1) * SIZE
            }
        }

        // Debug
        graphics {
            fill(Colors.BLACK) {
                circle(0, 0, 3)
            }
        }
    }
}