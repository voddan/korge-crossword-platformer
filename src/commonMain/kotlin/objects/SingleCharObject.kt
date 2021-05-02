package objects

import com.soywiz.korge.view.Container
import com.soywiz.korge.view.addUpdater
import views.Letter
import views.LetterBox

class SingleCharObject(char: Char) : Container() {
    val box = LetterBox()

    init {
        box.insertLetter(Letter(char))
        addChild(box)

        addUpdater {
            if(box.isEmpty) {
                this@SingleCharObject.removeFromParent()
            }
        }
    }
}