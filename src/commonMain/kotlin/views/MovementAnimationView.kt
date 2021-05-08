package views

import com.soywiz.korge.view.*
import com.soywiz.korma.geom.Anchor
import models.Movement
import models.State

class MovementAnimationView(
        movement: Movement,
        anchor: Anchor,
        idleAnimation: SpriteAnimation,
        walkAnimation: SpriteAnimation
) : Container() {
    init {
        val sprite = sprite(walkAnimation)
        sprite.anchor(anchor)

        addChild(sprite)

        addUpdater { dTime ->
            when (movement.state) {
                State.IDLE -> sprite.playAnimationLooped(idleAnimation)
                State.WALK -> sprite.playAnimation(walkAnimation)
            }
            sprite.scaleX = movement.directionFactor()
        }
    }
}