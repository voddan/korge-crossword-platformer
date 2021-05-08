package views

import com.soywiz.korge.view.*
import com.soywiz.korma.geom.Anchor

class MovementAnimationView(
        anchor: Anchor,
        idleAnimation: SpriteAnimation,
        walkAnimation: SpriteAnimation
) : Container() {
    var state: State = State.IDLE

    enum class State(val speed: Double) { IDLE(0.0), WALK(100.0) }

    var direction: Direction = Direction.RIGHT

    enum class Direction(val factor: Double) { LEFT(-1.0), RIGHT(1.0) }

    init {
        val sprite = sprite(walkAnimation)
        sprite.anchor(anchor)

        addChild(sprite)

        addUpdater { dTime ->
            when (state) {
                State.IDLE -> sprite.playAnimationLooped(idleAnimation)
                State.WALK -> sprite.playAnimation(walkAnimation)
            }
            sprite.scaleX = direction.factor
        }
    }
}