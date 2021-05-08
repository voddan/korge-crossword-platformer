package models

enum class State { IDLE, WALK }
enum class Direction { LEFT, RIGHT }

class Movement {
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
}