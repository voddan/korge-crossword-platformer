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
        if(input.keys[Key.RIGHT]) player.x += player.horizontalSpeed
        if(input.keys[Key.LEFT]) player.x -= player.horizontalSpeed
    }
}