package views

import com.soywiz.korge.view.Container
import com.soywiz.korge.view.Graphics
import com.soywiz.korge.view.graphics
import com.soywiz.korim.color.Colors
import com.soywiz.korma.geom.Point
import com.soywiz.korma.geom.Rectangle
import com.soywiz.korma.geom.vector.circle
import com.soywiz.korma.geom.vector.rect

class SkyBackgroundView(rect: Rectangle) : Container() {
    init {
        graphics {
            fill(Colors["#7dc6ff"]) {
                rect(rect)
            }

            cloud(Point(50, 30))
            cloud(Point(250, 40))
            cloud(Point(140, 80))
            cloud(Point(350, 85))
        }
    }
}

fun Graphics.cloud(anker: Point) {
    val color = Colors["#fffee8"]
    val width = 90.0
    val height = width * 2/3
    val radius = width / 4

    fill(color) { circle(anker + Point(radius, height - radius), radius) }
    fill(color) { circle(anker + Point(width - radius, height - radius), radius) }
    fill(color) { circle(anker + Point(width / 2, radius), radius) }
    fill(color) { rect(anker.x + radius, anker.y + height - radius, 2 * radius, radius) }
}