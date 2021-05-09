package components

import com.soywiz.korge.component.Component
import com.soywiz.korge.input.mouse
import com.soywiz.korge.view.View
import models.Movement

class HorizontalMouseMovementComponent(override val view: View, movement: Movement) : Component {
    init {
        view.mouse {
            onClick { events ->
                val event = events.lastEvent
                if(event.button.isLeft) {
                    val globalX = event.x.toDouble()
                    movement.navigateOnAutopilotTo(globalX)
                }
            }
        }
    }
}