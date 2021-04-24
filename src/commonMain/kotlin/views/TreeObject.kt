package views

import com.soywiz.korge.view.Container
import com.soywiz.korge.view.graphics
import com.soywiz.korim.color.Colors
import com.soywiz.korma.geom.shape.buildPath
import com.soywiz.korma.geom.vector.rect
import components.HorizontalShelf

class TreeObject : Container(), HorizontalShelf {
    val boxWidth = 100.0

    override val positionLine = buildPath {
        lineTo(boxWidth, 0.0)
    }

    init {
        graphics {
            fill(Colors.DARKGREEN) {
                rect(0.0, 0.0, boxWidth, -boxWidth * 1.5)
            }
        }

        val span = boxWidth / 4
        addLetter('T', 0.0, this)
        addLetter('R', span, this)
        addLetter('E', 2 * span, this)
        addLetter('E', 3 * span, this)
    }
}