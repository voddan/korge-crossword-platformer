package views

import com.soywiz.klock.TimeSpan
import com.soywiz.klock.milliseconds
import com.soywiz.korge.view.*
import com.soywiz.korim.color.Colors
import com.soywiz.korim.format.readBitmapSlice
import com.soywiz.korio.file.std.resourcesVfs
import com.soywiz.korma.geom.Anchor
import com.soywiz.korma.geom.vector.circle
import com.soywiz.korma.geom.vector.rect
import models.Loadable
import models.Movement
import objects.AbstractLetterObject

class Player : Container(), Loadable {
    public val movement = Movement(this, walkSpeed = 100.0)

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

        addUpdater { dTime ->
            movement.updatePosition(dTime)
        }
    }

    override suspend fun initLoad() {
        val sprite = MovementAnimationView(
            movement, Anchor(0.5, 0.8),
            loadSpriteAnimation("characters/Archer/Stand", spriteCount = 10, timePerFrame = 150.milliseconds),
            loadSpriteAnimation("characters/Archer/Run", spriteCount = 10, timePerFrame = 80.milliseconds)
        )

        addChild(sprite)
        sprite.scale = 0.2
    }
}

private suspend fun loadSpriteAnimation(dirPath: String, spriteCount: Int, timePerFrame: TimeSpan): SpriteAnimation = SpriteAnimation(
    sprites = (0..spriteCount - 1).map { i -> resourcesVfs["$dirPath/$i.png"].readBitmapSlice()},
    defaultTimePerFrame = timePerFrame
)