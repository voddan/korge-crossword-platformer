package views

import com.soywiz.klock.TimeSpan
import com.soywiz.klock.milliseconds
import com.soywiz.korev.Key
import com.soywiz.korge.input.keys
import com.soywiz.korge.view.Container
import com.soywiz.korge.view.SpriteAnimation
import com.soywiz.korge.view.addUpdater
import com.soywiz.korge.view.graphics
import com.soywiz.korge.view.hitShape
import com.soywiz.korge.view.sprite
import com.soywiz.korim.color.Colors
import com.soywiz.korim.format.readBitmapSlice
import com.soywiz.korio.file.std.resourcesVfs
import com.soywiz.korma.geom.degrees
import com.soywiz.korma.geom.minus
import com.soywiz.korma.geom.plus
import com.soywiz.korma.geom.vector.circle
import com.soywiz.korma.geom.vector.rect
import models.Loadable
import objects.AbstractLetterObject

class Player : Container(), Loadable {
    var state: State = State.IDLE
    enum class State(val speed: Double) { IDLE(0.0), WALK(100.0) }

    var direction: Direction = Direction.RIGHT
    enum class Direction(val factor: Double) { LEFT(-1.0), RIGHT(1.0) }

    init {
        graphics {
            fill(Colors.BLACK) {
                circle(0, 0, 3)
            }

            fill(Colors.YELLOW) {
                lineTo(-5.0, 0.0)
                lineTo(0.0, AbstractLetterObject.BOX_MARGIN_Y * 1.5)
                lineTo(5.0, 0.0)
                close()
            }
        }

        hitShape {
            rect(-5.0, 0.0, 10.0, AbstractLetterObject.BOX_MARGIN_Y)
        }
    }

    override suspend fun initLoad() {
        val idleAnimation = loadSpriteAnimation("characters/Archer/Stand", spriteCount = 10, timePerFrame = 150.milliseconds)
        val walkAnimation = loadSpriteAnimation("characters/Archer/Run", spriteCount = 10, timePerFrame = 80.milliseconds)

        val img = sprite(walkAnimation)
        img.anchorX = 0.5
        img.anchorY = 0.8
        val defaultScale = 0.2
        img.scale = defaultScale

        addChild(img)

        img.playAnimationLooped()

        addUpdater { dTime ->
            when (state) {
              State.IDLE -> img.playAnimationLooped(idleAnimation)
              State.WALK -> img.playAnimation(walkAnimation)
            }

            x += state.speed * direction.factor * dTime.seconds
            img.scaleX = direction.factor * defaultScale
        }

        keys {
            downFrame(Key.UP) {
                img.rotation += 1.degrees
            }
            downFrame(Key.DOWN) {
                img.rotation -= 1.degrees
            }
        }
    }
}

private suspend fun loadSpriteAnimation(dirPath: String, spriteCount: Int, timePerFrame: TimeSpan): SpriteAnimation = SpriteAnimation(
    sprites = (0..spriteCount - 1).map { i -> resourcesVfs["$dirPath/$i.png"].readBitmapSlice()},
    defaultTimePerFrame = timePerFrame
)