package components

import com.soywiz.korev.Key
import com.soywiz.korev.KeyEvent
import com.soywiz.korge.baseview.BaseView
import com.soywiz.korge.component.KeyComponent
import com.soywiz.korge.view.Views
import com.soywiz.korge.view.collidesWith
import com.soywiz.korge.view.onCollision
import com.soywiz.korio.async.launchImmediately
import kotlinx.coroutines.Dispatchers
import views.BackpackUI
import views.LetterBox
import views.Player

class LetterManipulatorKeyComponent(val player: Player, val backpack: BackpackUI) : KeyComponent {
    override val view: BaseView = player

    // LetterBox that Player touches at the moment
    // Needs the second check to avoid using onCollisionExit
    private var selectedBox: LetterBox? = null
        get() {
            val _field = field ?: return null
            if(player.collidesWith(_field)) {
                return _field
            } else {
                field = null
                return null
            }
        }

    init {
        player.onCollision(filter = {it is LetterBox}) { box ->
            selectedBox = box as LetterBox
        }
    }



    override fun Views.onKeyEvent(event: KeyEvent) {
        if(skipNoise(event.key)) return
        println(event.key)

        if(event.key == Key.SPACE) {
            clearBox(selectedBox)
        }

        if(event.key.isLetter()) {
            val value = event.key.name.first()
            swapWithBackpack(selectedBox, value)
        }
    }

    // TODO: why do I get duplicated key presses?
    // TODO: can the system be resilient to them
    private var lastKey: Key? = null
    private fun skipNoise(key: Key): Boolean {
        if(key != Key.SPACE && !key.isLetter()) return true

        if(key != lastKey) {
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
        if(letter != null) {
            backpack.appendLetter(letter)
        }
    }

    private fun swapWithBackpack(box: LetterBox?, value: Char) {
        box ?: return
        if(box.value == value) return
        val backpackLetter = backpack.findLetter(value) ?: return
        val backpackBox = backpack.findLetterBox(value) ?: return

        if(box.value != null) {
            backpack.swapLetters(backpackBox, box)
        } else {
            backpack.removeLetter(backpackLetter)
            launchImmediately(Dispatchers.Default) {
                box.moveLetter(backpackLetter)
            }
        }
    }

    fun Key.isLetter(): Boolean {
        val keyName = this.name
        if(keyName.length != 1) return false
        return keyName.first() in 'A'..'Z'
    }
}