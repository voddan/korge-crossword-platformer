package views

import com.soywiz.korge.tween.get
import com.soywiz.korge.tween.tween
import com.soywiz.korge.view.Container
import com.soywiz.korge.view.addUpdater
import com.soywiz.korge.view.shapeView
import com.soywiz.korge.view.solidRect
import com.soywiz.korim.color.Colors
import com.soywiz.korio.async.launchImmediately
import com.soywiz.korma.geom.Point
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class BackpackUI : Container() {
    companion object const {
        const val MIN_WIDTH = 500.0
        const val LEFT_MARGIN = 15.0
        const val MARGIN = Letter.SIZE * 0.8
        const val POS_EPSILON = 0.1
        val LETTER_MOVE_TIME = Letter.RETURN_TO_POS_TIME
    }

    private fun childLetters(): List<Letter> = children.filterIsInstance<Letter>().filter { it.state == Letter.State.PARKED }
    private fun supposedLetterPos(): Map<Letter, Point> = childLetters()
            .sortedBy { it.value }
            .mapIndexed { index, letter -> letter to Point(LEFT_MARGIN + index * (LetterBox.SIZE + MARGIN), 0.0) }
            .toMap()

    private val movingLetters: MutableMap<Letter, Job> = mutableMapOf()

    init {
        shapeView {
            val rect = solidRect(MIN_WIDTH, 5.0, color = Colors["#c22aff"])
            rect.y = LetterBox.SIZE
        }

        addUpdater {
            for ((letter, correctPos) in supposedLetterPos()) {
                if (letter.pos.distanceTo(correctPos) > POS_EPSILON) {
                    if (movingLetters.get(letter)?.isActive == true) continue

                    movingLetters[letter] = launchImmediately(Dispatchers.Default) {
                        letter.tween(letter::x[correctPos.x], letter::y[correctPos.y], time = LETTER_MOVE_TIME)
                    }
                }
            }
        }
    }

    public fun nextLetterPos(): Point {
        val step = LetterBox.SIZE + MARGIN
        return when(val size = childLetters().size) {
            0 -> Point(LEFT_MARGIN, 0.0)
            1 -> Point(LEFT_MARGIN + step, 0.0)
            else -> Point( 0.5 * (LEFT_MARGIN + size * step), 30.0)
        }
    }

    public fun findLetter(value: Char): Letter? {
        return childLetters().firstOrNull { it.value == value }
    }
}