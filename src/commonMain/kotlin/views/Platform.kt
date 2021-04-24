package views

import com.soywiz.korge.view.Container
import com.soywiz.korge.view.graphics
import com.soywiz.korim.color.Colors
import com.soywiz.korma.geom.Rectangle
import com.soywiz.korma.geom.shape.Shape2d
import com.soywiz.korma.geom.shape.bounds
import com.soywiz.korma.geom.shape.buildPath
import com.soywiz.korma.geom.shape.ops.intersection
import com.soywiz.korma.geom.shape.toShape2d
import com.soywiz.korma.geom.vector.*


class Platform(val rect: Rectangle) : Container() {
    lateinit var shape: VectorPath

    init {
        val span = rect.width / 5
        val step = rect.height / 3

        val topLine: VectorPath = buildPath {
            moveTo(rect.left, rect.top)
            rLineToH(span)
            rLineTo(span, step)
            rLineToH(span / 3)
            rLineToV(step)
            rLineToH(span / 3)
            rLineToV(- step)
            rLineToH(span / 3)
        }

        shape = buildPath {
            path(topLine)
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

    public fun topPositionY(x: Double): Double {
        val cutLine = Shape2d.Rectangle(x, rect.top, x, rect.bottom)
        val shape2d = shape.toShape2d(closed = false)
        val intersect = shape2d.intersection(cutLine)
        return intersect.bounds.top
    }
}