package scenes

import com.soywiz.korge.scene.Scene
import com.soywiz.korge.view.Container
import com.soywiz.korge.view.Graphics
import com.soywiz.korge.view.graphics
import com.soywiz.korim.color.Colors
import com.soywiz.korma.geom.Point
import com.soywiz.korma.geom.Rectangle
import com.soywiz.korma.geom.vector.circle
import com.soywiz.korma.geom.vector.rect
import views.Platform
import views.Player

class GameScene() : Scene() {
    lateinit var player: Player
    lateinit var platform: Platform

    override suspend fun Container.sceneInit() {
        addBackground()

        platform = Platform(Rectangle.fromBounds(0, views.virtualHeight * 3/4, views.virtualWidth, views.virtualHeight))
        addChild(platform)

        player = Player(platform)
        player.x = 50.0
        addChild(player)
    }

    fun Container.addBackground() {
        graphics {
            fill(Colors["#7dc6ff"]) {
                rect(0, 0, views.virtualWidth, views.virtualHeight)
            }

            cloud(Point(50, 30))
            cloud(Point(250, 40))
            cloud(Point(140, 80))
            cloud(Point(350, 85))
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
}