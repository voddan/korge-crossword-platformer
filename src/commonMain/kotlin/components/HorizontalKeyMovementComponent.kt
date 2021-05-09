package components

import com.soywiz.klock.TimeSpan
import com.soywiz.korev.Key
import com.soywiz.korge.component.UpdateComponentWithViews
import com.soywiz.korge.view.View
import com.soywiz.korge.view.Views
import models.Direction
import models.Movement
import models.State

class HorizontalKeyMovementComponent(override val view: View, val movement: Movement) : UpdateComponentWithViews {
    override fun update(views: Views, dt: TimeSpan) {
        when {
            views.keys.pressing(Key.LEFT) -> {
                movement.onAutopilot = false
                movement.state = State.WALK
                movement.direction = Direction.LEFT
            }
            views.keys.pressing(Key.RIGHT) -> {
                movement.onAutopilot = false
                movement.state = State.WALK
                movement.direction = Direction.RIGHT
            }
            else -> movement.state = State.IDLE
        }
    }
}