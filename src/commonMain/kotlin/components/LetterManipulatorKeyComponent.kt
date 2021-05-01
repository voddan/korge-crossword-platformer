package components

import com.soywiz.korev.Key
import com.soywiz.korev.KeyEvent
import com.soywiz.korge.baseview.BaseView
import com.soywiz.korge.component.KeyComponent
import com.soywiz.korge.view.Views
import views.BackpackUI
import views.LetterBox
import views.MovementAnimator
import views.Player
import views.moveViewToParent

class LetterManipulatorKeyComponent(val player: Player, val backpack: BackpackUI, val movementAnimator: MovementAnimator) : KeyComponent {
    override val view: BaseView = player

    private val boxSelector = SelectCollidingLetterBoxComponent(player)

    init {
        player.addComponent(boxSelector)
    }

    override fun Views.onKeyEvent(event: KeyEvent) {
        if (skipNoise(event.key)) return
        println(event.key)

        if (event.key == Key.SPACE) {
            sendToBackpack(boxSelector.selection())
        }

        if (event.key.isLetter()) {
            val value = event.key.name.first()
            swapWithBackpack(boxSelector.selection(), value)
        }
    }

    // TODO: why do I get duplicated key presses?
    // TODO: can the system be resilient to them
    private var lastKey: Key? = null
    private fun skipNoise(key: Key): Boolean {
        if (key != Key.SPACE && !key.isLetter()) return true

        if (key != lastKey) {
            lastKey = key
            return true
        } else {
            lastKey = null
            return false
        }
    }

    private fun sendToBackpack(box: LetterBox?) {
        val letter = box?.letter ?: return
        movementAnimator.moveViewToParent(letter, backpack, dPos = backpack.nextLetterPos())
    }

    private fun swapWithBackpack(selectedBox: LetterBox?, newValue: Char) {
        selectedBox ?: return
        if (selectedBox.value == newValue) return
        val backpackLetter = backpack.findLetter(newValue) ?: return

        movementAnimator.moveViewToParent(backpackLetter, selectedBox)
        if (!selectedBox.isEmpty) {
            movementAnimator.moveViewToParent(selectedBox.letter!!, backpack, dPos = backpack.nextLetterPos())
        }
    }

    fun Key.isLetter(): Boolean {
        val keyName = this.name
        if (keyName.length != 1) return false
        return keyName.first() in 'A'..'Z'
    }
}