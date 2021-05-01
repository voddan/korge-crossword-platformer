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
import models.HorizontalShelf
import models.putOnShelf

class LetterBox : Container() {
    companion object const {
        const val SIZE = Letter.SIZE + 2
        const val HITBOX_HEIGHT = 100.0
    }

    private var letter: Letter? = null
    public val value: Char? get() = letter?.value

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
        this.letter = letter
        addChild(letter)
        letter.pos = Point(0.0, 0.0)
    }

    public fun removeLetter(): Letter? {
        val letter = this.letter
        this.letter = null
        return letter
    }
}

fun Container.addLetterBox(posX: Double, shelf: HorizontalShelf): LetterBox {
    val box = LetterBox()
    box.x = posX
    putOnShelf(box, shelf)
    addChild(box)
    return box
}