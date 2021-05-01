package views

import com.soywiz.korge.view.Container
import com.soywiz.korge.view.shapeView
import com.soywiz.korge.view.solidRect
import com.soywiz.korim.color.Colors
import com.soywiz.korma.geom.Point

class BackpackUI : Container() {
    companion object const {
        const val MIN_WIDTH = 500.0
        const val LEFT_MARGIN = 15.0
        const val MARGIN = Letter.SIZE * 0.8
    }

    init {
        shapeView {
            val rect = solidRect(MIN_WIDTH, 5.0, color = Colors["#c22aff"])
            rect.y = LetterBox.SIZE
        }
    }

    public fun nextLetterPos(): Point {
        val step = LetterBox.SIZE + MARGIN
        val letterIndex = children.count { it is Letter }
        return Point(LEFT_MARGIN + letterIndex * step, 0.0)
    }

    public fun findLetter(value: Char): Letter? {
        return children.firstOrNull { it is Letter && it.value == value } as Letter?
    }
}