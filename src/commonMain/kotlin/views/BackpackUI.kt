package views

import com.soywiz.korge.view.Container
import com.soywiz.korge.view.graphics
import com.soywiz.korge.view.solidRect
import com.soywiz.korim.color.Colors
import com.soywiz.korio.async.launchImmediately
import com.soywiz.korma.geom.shape.buildPath
import com.soywiz.korma.geom.vector.VectorPath
import com.soywiz.korma.geom.vector.rLineToH
import components.HorizontalShelf
import kotlinx.coroutines.Dispatchers

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
        graphics {
            val rect = solidRect(MIN_WIDTH, 5.0, color = Colors["#c22aff"])
            rect.y = LetterBox.SIZE
        }
    }

    private val collectedLetters = mutableListOf<Letter>()

    public fun addLetter(letter: Letter) {
        collectedLetters.add(letter)

        val step = LetterBox.SIZE + MARGIN
        val letterIndex = collectedLetters.size - 1
        val letterBox = addLetterBox(LEFT_MARGIN + letterIndex * step, this)

        launchImmediately(Dispatchers.Default) {
            letterBox.moveLetterTo(letter)
        }
    }
}