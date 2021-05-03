package views

import com.soywiz.korge.view.Container
import com.soywiz.korge.view.graphics
import com.soywiz.korge.view.hitShape
import com.soywiz.korge.view.image
import com.soywiz.korim.color.Colors
import com.soywiz.korim.format.readBitmap
import com.soywiz.korio.file.std.resourcesVfs
import com.soywiz.korma.geom.vector.circle
import com.soywiz.korma.geom.vector.rect
import models.Loadable

class Player : Container(), Loadable {
    companion object const {
        const val SPEED = 5.0
    }

    init {
        graphics {
            fill(Colors.BLACK) {
                circle(0, 0, 3)
            }

            fill(Colors.YELLOW) {
                lineTo(-5.0, 0.0)
                lineTo(0.0, 20.0)
                lineTo(5.0, 0.0)
                close()
            }
        }

        hitShape {
            rect(-5, 0, 10, 20)
        }
    }

    override suspend fun initLoad() {
        val img = image(resourcesVfs["characters/Archer/Walk/5.png"].readBitmap())
        img.scale = 0.2
        img.x = - img.scaledWidth * 0.5
        img.y = - img.scaledHeight * 0.8
        addChild(img)
    }
}