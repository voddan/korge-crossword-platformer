package objects

import com.soywiz.korge.view.Container
import com.soywiz.korge.view.addUpdater
import com.soywiz.korio.lang.assert
import models.Loadable
import views.DisappearingView
import views.Letter
import views.LetterBox

abstract class AbstractLetterObject(val word: String) : Container(), Loadable {
    companion object const {
        val BOX_MARGIN_Y = 10.0
    }

    abstract suspend fun initObjectView(): DisappearingView

    private lateinit var objectView: DisappearingView
    private lateinit var boxes: List<LetterBox>

    override suspend fun initLoad() {
        objectView = initObjectView()
        addChild(objectView)

        val boxStep = (objectView.scaledWidth - LetterBox.SIZE) / (word.length - 1)  // word >= 2

        boxes  = word.mapIndexed { i, ch ->
            val box = LetterBox()
            box.x = boxStep * i
            box.y = BOX_MARGIN_Y
            addChild(box)
            box
        }

        addUpdater {
            val completeness = countCorrectLetters().toDouble() / word.length
            objectView.transparency = 1 - completeness
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