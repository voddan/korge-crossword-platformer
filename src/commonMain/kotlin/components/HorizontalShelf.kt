package components

import com.soywiz.korma.geom.shape.Shape2d
import com.soywiz.korma.geom.shape.bounds
import com.soywiz.korma.geom.shape.buildPath
import com.soywiz.korma.geom.shape.ops.intersection
import com.soywiz.korma.geom.shape.toShape2d
import com.soywiz.korma.geom.vector.VectorPath
import com.soywiz.korma.geom.vector.lineToV
import com.soywiz.korma.geom.vector.path

interface HorizontalShelf {
    val positionLine: VectorPath

    private fun rect() = positionLine.getBounds()

    private fun shape() = buildPath {
        val rect = rect()
        val bottomMargine = 10  // To have some height when the last line is the bottom
        path(positionLine)
        lineToV(rect.bottom + bottomMargine)
        lineTo(rect.left, rect.bottom + bottomMargine)
        close()
    }

    fun positionY(x: Double): Double {
        val rect = rect()
        val cutLine = Shape2d.Rectangle(x, rect.top, x, rect.bottom)
        val shape2d = shape().toShape2d(closed = false)
        val intersect = shape2d.intersection(cutLine)
        return intersect.bounds.top
    }
}
