package components

import com.soywiz.korev.Key
import com.soywiz.korev.KeyEvent
import com.soywiz.korge.baseview.BaseView
import com.soywiz.korge.component.KeyComponent
import com.soywiz.korge.view.Views
import views.*

class LetterManipulatorKeyComponent(val player: Player, val backpack: BackpackUI, val translateViewAnimator: TranslateViewAnimator) : KeyComponent {
    override val view: BaseView = player

    private val boxSelector = SelectCollidingLetterBoxComponent(player).also { player.addComponent(it) }

    override fun Views.onKeyEvent(event: KeyEvent) {
        if(!event.typeDown) return

        if (event.key == Key.SPACE) {
            sendToBackpack(boxSelector.selection())
        }

        if (event.key.isLetter()) {
            val value = event.key.name.first()
            swapWithBackpack(boxSelector.selection(), value)
        }
    }

    private fun sendToBackpack(box: LetterBox?) {
        val letter = box?.letter ?: return
        translateViewAnimator.translateViewToParent(letter, backpack, dPos = backpack.nextLetterPos())
    }

    private fun swapWithBackpack(selectedBox: LetterBox?, newValue: Char) {
        selectedBox ?: return
        if (selectedBox.value == newValue) return
        val backpackLetter = backpack.findLetter(newValue) ?: return

        translateViewAnimator.translateViewToParent(backpackLetter, selectedBox)
        if (!selectedBox.isEmpty) {
            translateViewAnimator.translateViewToParent(selectedBox.letter!!, backpack, dPos = backpack.nextLetterPos())
        }
    }
}

fun Key.isLetter(): Boolean {
    val keyName = this.name
    if (keyName.length != 1) return false
    return keyName.first() in 'A'..'Z'
}