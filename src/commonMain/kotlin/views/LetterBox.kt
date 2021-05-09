package views

import com.soywiz.korge.view.Container
import com.soywiz.korge.view.Graphics
import com.soywiz.korge.view.addUpdater
import com.soywiz.korge.view.hitShape
import com.soywiz.korim.color.Colors
import com.soywiz.korim.paint.Paint
import com.soywiz.korim.vector.StrokeInfo
import com.soywiz.korma.geom.Point
import com.soywiz.korma.geom.vector.rect
import com.soywiz.korma.geom.vector.rectHole

class LetterBox(val correctValue: Char? = null) : Container() {
    companion object const {
        const val SIZE = Letter.SIZE + 2
        const val HITBOX_HEIGHT = 100.0
    }

    public val letter: Letter? get() = children.singleOrNull { it is Letter } as Letter?
    public val value: Char? get() = letter?.value
    public val isEmpty: Boolean get() = (letter == null)


    init {
        val graphics = Graphics()
        addChild(graphics)

        hitShape {
            rect(0.0, 0.0, SIZE, -HITBOX_HEIGHT)
        }

        addUpdater {
            when(value) {
                null -> graphics.drawLetterBox(Colors.BLACK, thickness = 1.5)
                correctValue -> graphics.drawLetterBox(Colors.BLUE, thickness = 2.5)
                else -> graphics.drawLetterBox(Colors.RED, thickness = 2.5)
            }
        }
    }

    public fun insertLetter(letter: Letter) {
        addChild(letter)
        letter.pos = Point(0.0, 0.0)
    }
}

private fun Graphics.drawLetterBox(paint: Paint, thickness: Double) {
    clear()
    stroke(paint = paint, info = StrokeInfo(thickness = thickness)) {
        rectHole(-1.0, -1.0, LetterBox.SIZE, LetterBox.SIZE)
    }
}