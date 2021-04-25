package views

import com.soywiz.korge.view.Container
import com.soywiz.korge.view.graphics
import com.soywiz.korim.color.Colors
import com.soywiz.korim.vector.StrokeInfo
import com.soywiz.korma.geom.vector.rectHole
import components.HorizontalShelf
import components.putOnShelf

class LetterBox : Container() {
    companion object const {
        const val SIZE = Letter.SIZE + 2
    }

    private var letter: Letter? = null
    public val value: Char? get() = letter?.value

    init {
        graphics {
            stroke(paint = Colors.BLACK, info = StrokeInfo(thickness = 1.5)) {
                rectHole(0.0, 0.0, SIZE, SIZE)
            }
        }
    }

    public fun insertLetter(letter: Letter) {
        cleanLetter()
        this.letter = letter
        addChild(letter)
    }

    private fun cleanLetter() {
        if(letter != null) {
            removeChild(letter)
        }
    }

}

fun Container.addLetterBox(posX: Double, shelf: HorizontalShelf): LetterBox {
    val box = LetterBox()
    box.x = posX
    putOnShelf(box, shelf)
    addChild(box)
    return box
}