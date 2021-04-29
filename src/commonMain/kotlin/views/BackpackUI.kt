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

class BackpackUI(val movementAnimator: MovementAnimator) : Container(), HorizontalShelf {
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
//        boxesCount ++

        val box = nextLetterParent()
        movementAnimator.moveViewToParent(letter, box) {
            box.insertLetter(letter)
        }
    }

    public fun nextLetterPos(): Point {
        val step = LetterBox.SIZE + MARGIN
        val letterIndex = collectedLetters.size
        return Point(LEFT_MARGIN + letterIndex * step, 0.0)
    }

    public fun nextLetterParent(): LetterBox {
        val box = LetterBox()
        box.pos = nextLetterPos()
        addChild(box)
        return box
    }

    public fun swapLetters(box1: LetterBox, box2: LetterBox) {
        box1.value ?: return
        box2.value ?: return
        if(box1.value == box2.value) return

        val letter1 = box1.removeLetter()!!
        val letter2 = box2.removeLetter()!!

        val index = collectedLetters.indexOf(letter1)
        collectedLetters.set(index, letter2)

        movementAnimator.moveViewToParent(letter1, box2) {
            box2.insertLetter(letter1)
        }
        movementAnimator.moveViewToParent(letter2, box1) {
            box1.insertLetter(letter2)
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
        val letter = findLetter(value)
        val box = letter?.parent as? LetterBox
//        assert(box?.firstChild == letter)
        return box
    }
}