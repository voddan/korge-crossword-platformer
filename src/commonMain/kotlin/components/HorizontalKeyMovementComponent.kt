package components

import com.soywiz.klock.TimeSpan
import com.soywiz.korev.Key
import com.soywiz.korge.component.UpdateComponentWithViews
import com.soywiz.korge.view.View
import com.soywiz.korge.view.Views
import views.MovementAnimationView

class HorizontalKeyMovementComponent(override val view: View, val animation: MovementAnimationView) : UpdateComponentWithViews {
    override fun update(views: Views, dt: TimeSpan) {
        when {
            views.keys.pressing(Key.LEFT) -> {
                animation.state = MovementAnimationView.State.WALK
                animation.direction = MovementAnimationView.Direction.LEFT
            }
            views.keys.pressing(Key.RIGHT) -> {
                animation.state = MovementAnimationView.State.WALK
                animation.direction = MovementAnimationView.Direction.RIGHT
            }
            else -> animation.state = MovementAnimationView.State.IDLE
        }

        view.x += animation.state.speed * animation.direction.factor * dt.seconds
    }
}