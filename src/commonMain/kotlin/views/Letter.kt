package views

import com.soywiz.korge.view.Container
import com.soywiz.korge.view.graphics
import com.soywiz.korge.view.solidRect
import com.soywiz.korge.view.text
import com.soywiz.korim.color.Colors
import com.soywiz.korma.geom.Point
import com.soywiz.korma.geom.vector.circle
import components.HorizontalShelf
import components.putOnShelf

class Letter(val value: Char) : Container() {
    val boxSize = 20.0

    init {
        graphics {
            solidRect(boxSize, boxSize, color = Colors.LIGHTYELLOW)
            text("$value", color = Colors["#c22aff"], textSize = boxSize) {
                pos = Point(0.2, -0.1) * boxSize
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

fun Container.addLetter(value: Char, posX: Double, shelf: HorizontalShelf): Letter {
    val letter = Letter(value)
    letter.x = posX
    putOnShelf(letter, shelf)
    addChild(letter)
    return letter
}