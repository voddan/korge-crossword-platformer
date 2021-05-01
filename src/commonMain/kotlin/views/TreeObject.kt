package views

import com.soywiz.korge.view.graphics
import com.soywiz.korim.color.Colors
import com.soywiz.korim.vector.StrokeInfo
import com.soywiz.korma.geom.vector.rect
import com.soywiz.korma.geom.vector.rectHole

class TreeObject(initialWord: String) : LetterObject("TREE") {
    companion object const {
        const val WIDTH = 100.0
        const val HEIGHT = WIDTH * 1.5
    }

    override fun initContours() = graphics {
        fill(Colors.DARKGREEN) {
            rect(0.0, 0.0, WIDTH, -HEIGHT)
        }
    }

    override fun initGraphics() = graphics {
        stroke(Colors["#142164"], StrokeInfo()) {
            rectHole(0.0, 0.0, WIDTH, -HEIGHT)
        }
    }

    init {
       insertLetters(initialWord)
    }
}