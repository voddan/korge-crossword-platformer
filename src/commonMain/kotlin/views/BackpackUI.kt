package views

import com.soywiz.korge.view.Container
import com.soywiz.korge.view.shapeView
import com.soywiz.korge.view.solidRect
import com.soywiz.korim.color.Colors
import com.soywiz.korio.async.launchImmediately
import com.soywiz.korma.geom.shape.buildPath
import com.soywiz.korma.geom.vector.VectorPath
import com.soywiz.korma.geom.vector.rLineToH
import kotlinx.coroutines.Dispatchers
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
//    private var boxesCount = 0

    public fun appendLetter(letter: Letter) {
        collectedLetters.add(letter)

        val step = LetterBox.SIZE + MARGIN
        val letterIndex = collectedLetters.size
        val letterBox = addLetterBox(LEFT_MARGIN + letterIndex * step, this)
//        boxesCount ++

        launchImmediately(Dispatchers.Default) {
            letterBox.moveLetter(letter)
        }
    }

    public fun swapLetters(box1: LetterBox, box2: LetterBox) {
        box1.value ?: return
        box2.value ?: return
        if(box1.value == box2.value) return

        val letter1 = box1.removeLetter()!!
        val letter2 = box2.removeLetter()!!

        val index = collectedLetters.indexOf(letter1)
        collectedLetters.set(index, letter2)

        launchImmediately(Dispatchers.Default) {
            box1.moveLetter(letter2)
        }

        launchImmediately(Dispatchers.Default) {
            box2.moveLetter(letter1)
        }
    }

    public fun removeLetter(letter: Letter): LetterBox? {
        collectedLetters.remove(letter)
        val box = letter.parent as? LetterBox ?: return null
        box.removeLetter()
        return box
    }

    public fun findLetter(value: Char): Letter? {
        return collectedLetters.lastOrNull { it.value == value }
    }

    public fun findLetterBox(value: Char): LetterBox? {
        val letter = collectedLetters.lastOrNull { it.value == value }
        return letter?.parent as? LetterBox
    }
}