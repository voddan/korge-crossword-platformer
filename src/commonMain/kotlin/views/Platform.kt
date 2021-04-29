package views

import com.soywiz.korge.view.Container
import com.soywiz.korge.view.graphics
import com.soywiz.korim.color.Colors
import com.soywiz.korma.geom.Rectangle
import com.soywiz.korma.geom.shape.buildPath
import com.soywiz.korma.geom.vector.*
import models.HorizontalShelf


class Platform(val rect: Rectangle) : Container(), HorizontalShelf {
    val span = rect.width / 5
    val step = rect.height / 3

    override val positionLine: VectorPath = buildPath {
        moveTo(rect.left, rect.top)
        rLineToH(span)
        rLineTo(span, step)
        rLineToH(span / 3)
        rLineToV(step)
        rLineToH(span / 3)
//            rLineToV(- step)
//            rLineToH(span / 3)
    }

    init {
        val shape = buildPath {
            path(positionLine)
            lineToV(rect.bottom)
            lineTo(rect.left, rect.bottom)
            close()
        }

        graphics {
            fill(Colors.GREEN) {
               path(shape)
            }
        }
    }
}