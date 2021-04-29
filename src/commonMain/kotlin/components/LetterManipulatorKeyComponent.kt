package components

import com.soywiz.korev.Key
import com.soywiz.korev.KeyEvent
import com.soywiz.korge.baseview.BaseView
import com.soywiz.korge.component.KeyComponent
import com.soywiz.korge.view.Views
import com.soywiz.korma.geom.Point
import views.*

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
            clearBox(boxSelector.selection())
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

    private fun clearBox(box: LetterBox?) {
        box ?: return
        val letter = box.removeLetter()
        if (letter != null) {
            movementAnimator.moveViewTo(letter, backpack.localToGlobal(backpack.nextLetterPos())) {
                letter.pos = Point(0.0, 0.0)
                backpack.appendLetter(letter)
            }
        }
    }

    private fun swapWithBackpack(selectedBox: LetterBox?, newValue: Char) {
        selectedBox ?: return
        if (selectedBox.value == newValue) return
        val backpackLetter = backpack.findLetter(newValue) ?: return
        val backpackBox = backpack.findLetterBox(newValue) ?: return

        if (selectedBox.value != null) {
            if(backpackLetter.value == selectedBox.value) return

            backpackBox.removeLetter()!!
            val selectedLetter = selectedBox.removeLetter()!!

            backpack.replaceLetter(backpackLetter, selectedLetter)

            movementAnimator.moveViewToParent(backpackLetter, selectedBox) {
                selectedBox.insertLetter(backpackLetter)
            }
            movementAnimator.moveViewToParent(selectedLetter, backpackBox) {
                backpackBox.insertLetter(selectedLetter)
            }
        } else {
            backpack.removeLetter(backpackLetter)
            movementAnimator.moveViewToParent(backpackLetter, selectedBox) {
                selectedBox.insertLetter(backpackLetter)
            }
        }
    }

    fun Key.isLetter(): Boolean {
        val keyName = this.name
        if (keyName.length != 1) return false
        return keyName.first() in 'A'..'Z'
    }
}