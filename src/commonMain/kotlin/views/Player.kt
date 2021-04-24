package views

import com.soywiz.korge.view.Container
import com.soywiz.korge.view.graphics
import com.soywiz.korim.color.Colors
import com.soywiz.korma.geom.vector.circle
import com.soywiz.korma.geom.vector.rect

class Player : Container() {
    val horizontalSpeed = 5.0

    init {
        graphics {
            fill(Colors.RED) {
                rect(-12, 0, 24, -55)
            }
            fill(Colors.BLACK) {
                circle(0, 0, 3)
            }
        }
    }
}