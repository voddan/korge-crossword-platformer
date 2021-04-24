package views

import com.soywiz.korev.Key
import com.soywiz.korev.KeyEvent
import com.soywiz.korge.baseview.BaseView
import com.soywiz.korge.component.KeyComponent
import com.soywiz.korge.view.Container
import com.soywiz.korge.view.Views
import com.soywiz.korge.view.graphics
import com.soywiz.korim.color.Colors
import com.soywiz.korma.geom.vector.circle
import com.soywiz.korma.geom.vector.rect

class Player(val platform: Platform) : Container() {
    init {
        graphics {
            fill(Colors.RED) {
                rect(-12, 0, 24, -55)
            }
            fill(Colors.BLACK) {
                circle(0, 0, 3)
            }
        }

        addComponent(PlayerKeyboardControls(this, platform))
    }
}

class PlayerKeyboardControls(val player: Player, val platform: Platform) : KeyComponent {
    override val view: BaseView = player

    init {
        player.y = platform.topPositionY(player.x)
    }

    override fun Views.onKeyEvent(event: KeyEvent) {
        if(input.keys[Key.RIGHT]) player.x += 1
        if(input.keys[Key.LEFT]) player.x -= 1

        player.y = platform.topPositionY(player.x)
    }
}