package views

import com.soywiz.korev.Key
import com.soywiz.korev.KeyEvent
import com.soywiz.korge.baseview.BaseView
import com.soywiz.korge.component.KeyComponent
import com.soywiz.korge.view.Container
import com.soywiz.korge.view.Views
import com.soywiz.korge.view.graphics
import com.soywiz.korim.color.Colors
import com.soywiz.korma.geom.vector.roundRect

class Player : Container() {
    init {
        graphics {
            fill(Colors.RED) {
                roundRect(10, 10, 50, 25, 5)
            }
        }

        addComponent(PlayerKeyboardControls(this))
    }
}

class PlayerKeyboardControls(val player: Player) : KeyComponent {
    override val view: BaseView = player

    override fun Views.onKeyEvent(event: KeyEvent) {
        if(input.keys[Key.RIGHT]) player.x += 1
        if(input.keys[Key.DOWN]) player.y += 1
        if(input.keys[Key.LEFT]) player.x -= 1
        if(input.keys[Key.UP]) player.y -= 1
    }
}