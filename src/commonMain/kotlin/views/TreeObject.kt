package views

import com.soywiz.korge.view.Container
import com.soywiz.korge.view.graphics
import com.soywiz.korim.color.Colors
import com.soywiz.korma.geom.shape.buildPath
import com.soywiz.korma.geom.vector.rect
import components.HorizontalShelf

class TreeObject : Container(), HorizontalShelf {
    companion object const {
        const val WIDTH = 100.0
        const val HEIGHT = WIDTH * 1.5
    }

    override val positionLine = buildPath {
        lineTo(WIDTH, 0.0)
    }

    init {
        graphics {
            fill(Colors.DARKGREEN) {
                rect(0.0, 0.0, WIDTH, -HEIGHT)
            }
        }

        val span = WIDTH / 4

        addLetterBox(0.0, this).insertLetter(Letter('T'))
        addLetterBox(span, this).insertLetter(Letter('R'))
        addLetterBox(2 * span, this).insertLetter(Letter('E'))
        addLetterBox(3 * span, this).insertLetter(Letter('E'))
    }
}