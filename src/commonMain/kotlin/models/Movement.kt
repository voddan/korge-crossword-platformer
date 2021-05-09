package models

import com.soywiz.klock.TimeSpan
import com.soywiz.korge.view.View

enum class State { IDLE, WALK }
enum class Direction { LEFT, RIGHT }

class Movement(val character: View, val walkSpeed: Double) {
    var state: State = State.IDLE
    var direction: Direction = Direction.RIGHT

    fun directionFactor(): Double = when(direction) {
        Direction.LEFT -> -1.0
        Direction.RIGHT -> 1.0
    }

    fun speedFactor(): Double = when(state) {
        State.IDLE -> 0.0
        State.WALK -> directionFactor()
    }

    fun updatePosition(dTime: TimeSpan) {
        if(onAutopilot) {
            state = State.WALK
            direction = directionFrom(globalDestinationX - character.globalX)

            if(direction != destinationDirection) {
                onAutopilot = false
                state = State.IDLE
                direction = destinationDirection
            }
        }

        character.x += speedFactor() * walkSpeed * dTime.seconds
    }

    var onAutopilot: Boolean = false
    private var globalDestinationX: Double = 0.0
    private var destinationDirection: Direction = Direction.RIGHT

    fun navigateOnAutopilotTo(globalX: Double) {
        onAutopilot = true
        globalDestinationX = globalX
        destinationDirection = directionFrom(globalX - character.globalX)
    }
}

fun directionFrom(value: Double): Direction = if(value >= 0.0) Direction.RIGHT else Direction.LEFT