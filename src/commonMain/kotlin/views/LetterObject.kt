package views

import com.soywiz.korge.view.Container
import com.soywiz.korge.view.Graphics
import com.soywiz.korge.view.addUpdater
import com.soywiz.korio.lang.assert

abstract class LetterObject(val word: String) : Container() {
    abstract fun initGraphics(): Graphics
    abstract fun initContours(): Graphics

    val graphics: Graphics by lazy { initGraphics() }
    val contours: Graphics by lazy { initContours() }

    val graphicWidth = maxOf(graphics.globalBounds.width, contours.globalBounds.width)
    val boxStep = (graphicWidth - LetterBox.SIZE) / (word.length - 1)  // word >= 2

    val boxes: List<LetterBox> = word.mapIndexed { i, ch ->
        val box = LetterBox()
        box.x = boxStep * i
        addChild(box)
        box
    }

    init {
        addUpdater {
            val completeness = countCorrectLetters().toDouble() / word.length
            graphics.alpha = 1 - completeness
            contours.alpha = completeness
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