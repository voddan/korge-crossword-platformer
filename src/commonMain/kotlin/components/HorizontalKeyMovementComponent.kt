package components

import com.soywiz.korev.Key
import com.soywiz.korev.KeyEvent
import com.soywiz.korge.baseview.BaseView
import com.soywiz.korge.component.KeyComponent
import com.soywiz.korge.view.Views
import views.Player

class HorizontalKeyMovementComponent(val player: Player) : KeyComponent{
    override val view: BaseView = player

    override fun Views.onKeyEvent(event: KeyEvent) {
        player.state = Player.State.IDLE
        if(input.keys[Key.RIGHT]) {
            player.state = Player.State.WALK
            player.direction = Player.Direction.RIGHT
        }
        if(input.keys[Key.LEFT]) {
            player.state = Player.State.WALK
            player.direction = Player.Direction.LEFT
        }
    }
}