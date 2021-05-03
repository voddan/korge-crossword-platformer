package components

import com.soywiz.korge.baseview.BaseView
import com.soywiz.korge.component.Component
import com.soywiz.korge.view.View
import com.soywiz.korge.view.collidesWith
import com.soywiz.korge.view.onCollision
import views.LetterBox

class SelectCollidingLetterBoxComponent(val selector: View) : Component {
    override val view: BaseView = selector

    // LetterBox that [selector] touches at the moment
    private var selectedBox: LetterBox? = null

    init {
        selector.onCollision(filter = {it is LetterBox}) { box ->
            selectedBox = box as LetterBox
        }
    }

    // Needs the second check to avoid using onCollisionExit
    public fun selection(): LetterBox? {
        val selection = selectedBox ?: return null
        if(selector.collidesWith(selection)) {
            return selection
        } else {
            selectedBox = null
            return null
        }
    }
}