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
        addLetter('T', 0.0, this)
        addLetter('R', span, this)
        addLetter('E', 2 * span, this)
        addLetter('E', 3 * span, this)
    }
}