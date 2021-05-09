package components

import com.soywiz.korev.MouseEvent
import com.soywiz.korge.baseview.BaseView
import com.soywiz.korge.component.MouseComponent
import com.soywiz.korge.view.Views
import models.Movement
import views.Player

class HorizontalMouseMovementComponent(player: Player, val movement: Movement) : MouseComponent {
    override val view: BaseView = player

    override fun onMouseEvent(views: Views, event: MouseEvent) {
        if(event.button.isLeft && event.typeClick) {
            val globalX = event.x.toDouble()
            movement.navigateOnAutopilotTo(globalX)
        }
    }
}