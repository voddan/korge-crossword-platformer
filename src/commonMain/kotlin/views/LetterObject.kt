package views

import com.soywiz.korge.view.Container
import com.soywiz.korge.view.View
import com.soywiz.korge.view.addUpdater
import com.soywiz.korio.lang.assert
import models.Loadable

abstract class LetterObject(val word: String) : Container(), Loadable {
    abstract suspend fun initGraphics(): View
    abstract suspend fun initContours(graphics: View): View

    lateinit var graphics: View
    lateinit var contours: View

    var graphicWidth: Double = 0.0
    var boxStep: Double = 0.0

    lateinit var boxes: List<LetterBox>

    init {

    }

    override suspend fun initLoad() {
        graphics = initGraphics()
        contours = initContours(graphics)

        graphicWidth = maxOf(graphics.globalBounds.width, contours.globalBounds.width)
        boxStep = (graphicWidth - LetterBox.SIZE) / (word.length - 1)  // word >= 2

        boxes  = word.mapIndexed { i, ch ->
            val box = LetterBox()
            box.x = boxStep * i
            addChild(box)
            box
        }

        addUpdater {
            val completeness = countCorrectLetters().toDouble() / word.length
            graphics.alpha = completeness
            contours.alpha = 1 - completeness
        }
    }

    fun countCorrectLetters(): Int =
        boxes.zip(word.asIterable())
            .count {(box, char) -> box.value == char}

    fun insertLetters(insertWord: String) {
        assert(insertWord.length == word.length)
        assert(boxes.all { it.isEmpty })
        for (i in insertWord.indices) {
            val ch = insertWord[i]
            if(ch in 'A'..'Z') {
                boxes[i].insertLetter(Letter(ch))
            }
        }
    }

}