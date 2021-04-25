package views

import com.soywiz.korge.view.Container
import com.soywiz.korge.view.graphics
import com.soywiz.korim.color.Colors
import com.soywiz.korma.geom.shape.buildPath
import com.soywiz.korma.geom.vector.rect
import components.HorizontalShelf

class TreeObject(val backpack: BackpackUI) : Container(), HorizontalShelf {
    companion object const {
        const val WIDTH = 100.0
        const val HEIGHT = WIDTH * 1.5
        const val NUM_LETTERS = 4
    }

    override val positionLine = buildPath {
        lineTo(WIDTH, 0.0)
    }

    val letterBoxes = List(4) {i ->
        addLetterBox(i * WIDTH / NUM_LETTERS, this)
    }

    init {
        graphics {
            fill(Colors.DARKGREEN) {
                rect(0.0, 0.0, WIDTH, -HEIGHT)
            }
        }

        val span = WIDTH / 4

        letterBoxes[0].insertLetter(Letter('T'))
        letterBoxes[1].insertLetter(Letter('R'))
        letterBoxes[2].insertLetter(Letter('E'))
        letterBoxes[3].insertLetter(Letter('E'))

        for (box in letterBoxes) {
//            box.onCollision(filter = { it is Player }) {
//                val letter = box.removeLetter()
//                if(letter != null) {
//                    backpack.moveLetter(letter)
//                }
//            }
        }
    }
}