package views

import com.soywiz.korge.view.Container
import com.soywiz.korge.view.shapeView
import com.soywiz.korge.view.solidRect
import com.soywiz.korim.color.Colors
import com.soywiz.korma.geom.Point
import com.soywiz.korma.geom.shape.buildPath
import com.soywiz.korma.geom.vector.VectorPath
import com.soywiz.korma.geom.vector.rLineToH
import models.HorizontalShelf

class BackpackUI : Container(), HorizontalShelf {
    companion object const {
        const val MIN_WIDTH = 500.0
        const val LEFT_MARGIN = 15.0
        const val MARGIN = Letter.SIZE * 0.8
    }

    override val positionLine: VectorPath = buildPath {
        rLineToH(MIN_WIDTH * 10)
    }

    init {
        shapeView {
            val rect = solidRect(MIN_WIDTH, 5.0, color = Colors["#c22aff"])
            rect.y = LetterBox.SIZE
        }
    }

    private val collectedLetters = mutableListOf<Letter>()

    public fun appendLetter(letter: Letter) {
        addChild(letter)
        letter.pos = nextLetterPos()

        collectedLetters.add(letter)
    }

    public fun nextLetterPos(): Point {
        val step = LetterBox.SIZE + MARGIN
        val letterIndex = collectedLetters.size
        return Point(LEFT_MARGIN + letterIndex * step, 0.0)
    }

    fun replaceLetter(letter: Letter, newLetter: Letter) {
        collectedLetters.remove(letter)
        collectedLetters.add(newLetter)
        addChild(newLetter)
    }

    public fun removeLetter(letter: Letter) {
        collectedLetters.remove(letter)
    }

    public fun findLetter(value: Char): Letter? {
        return collectedLetters.lastOrNull { it.value == value }
    }
}