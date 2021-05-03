package views

import com.soywiz.korge.view.Container
import com.soywiz.korge.view.Graphics
import com.soywiz.korge.view.graphics
import com.soywiz.korge.view.hitShape
import com.soywiz.korim.color.Colors
import com.soywiz.korim.vector.StrokeInfo
import com.soywiz.korma.geom.Point
import com.soywiz.korma.geom.vector.rect
import com.soywiz.korma.geom.vector.rectHole

class LetterBox : Container() {
    companion object const {
        const val SIZE = Letter.SIZE + 2
        const val HITBOX_HEIGHT = 100.0
    }

    public val letter: Letter? get() = children.singleOrNull { it is Letter } as Letter?
    public val value: Char? get() = letter?.value
    public val isEmpty: Boolean get() = (letter == null)

    lateinit var graphics: Graphics

    init {
        graphics = graphics {
            stroke(paint = Colors.BLACK, info = StrokeInfo(thickness = 1.5)) {
                rectHole(0.0, 0.0, SIZE, SIZE)
            }
        }

        hitShape {
            rect(0.0, 0.0, SIZE, -HITBOX_HEIGHT)
        }
    }

    public fun insertLetter(letter: Letter) {
        addChild(letter)
        letter.pos = Point(0.0, 0.0)
    }
}