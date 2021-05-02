package scenes

import com.soywiz.korge.scene.Scene
import com.soywiz.korge.view.Container
import com.soywiz.korge.view.Graphics
import com.soywiz.korge.view.addTo
import com.soywiz.korge.view.graphics
import com.soywiz.korge.view.xy
import com.soywiz.korim.color.Colors
import com.soywiz.korma.geom.Point
import com.soywiz.korma.geom.Rectangle
import com.soywiz.korma.geom.vector.circle
import com.soywiz.korma.geom.vector.rect
import components.HorizontalKeyMovementComponent
import components.LetterManipulatorKeyComponent
import components.SelectCollidingLetterBoxComponent
import components.StayOnShelfComponent
import models.putOnShelf
import objects.MapleTreeObject
import objects.TreeObject
import views.BackpackUI
import views.MovementAnimator
import views.Platform
import views.Player
import views.addLetter

class GameScene() : Scene() {
    lateinit var platform: Platform

    override suspend fun Container.sceneInit() {
        addBackground()

        val movementAnimator = MovementAnimator()
        addChild(movementAnimator)

        val backpack = BackpackUI().apply {
            xy(40.0, 40.0)
            addTo(this@sceneInit)
        }

        platform = Platform(Rectangle.fromBounds(0, views.virtualHeight * 3/4, views.virtualWidth, views.virtualHeight))
        addChild(platform)

        TreeObject("TREE").apply {
            initLoad()
            x = 100.0
            putOnShelf(platform)
            addTo(this@sceneInit)
        }

        MapleTreeObject("**P**").apply {
            initLoad()
            x = 700.0
            putOnShelf(platform)
            addTo(this@sceneInit)
        }

        Player().apply {
            x = 50.0
            addComponent(StayOnShelfComponent(this, platform))
            addComponent(HorizontalKeyMovementComponent(this))
            addComponent(LetterManipulatorKeyComponent(this, backpack, movementAnimator))
            addComponent(SelectCollidingLetterBoxComponent(this))
            addTo(this@sceneInit)
        }

        addLetter('A', 300.0, platform)
        addLetter('B', 350.0, platform)

        // must be in front
        sendChildToFront(movementAnimator)
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