package scenes

import com.soywiz.korge.scene.Scene
import com.soywiz.korge.view.Container
import com.soywiz.korge.view.View
import com.soywiz.korge.view.addTo
import com.soywiz.korge.view.xy
import com.soywiz.korma.geom.Rectangle
import components.HorizontalKeyMovementComponent
import components.LetterManipulatorKeyComponent
import components.SelectCollidingLetterBoxComponent
import components.StayOnShelfComponent
import models.Loadable
import models.putOnShelf
import objects.CowObject
import objects.MapleTreeObject
import objects.SingleCharObject
import objects.TreeObject
import views.BackpackUI
import views.MovementAnimator
import views.Platform
import views.Player
import views.SkyBackgroundView

class GameScene() : Scene() {
    lateinit var platform: Platform

    override suspend fun Container.sceneInit() {
        SkyBackgroundView(Rectangle(0, 0, views.virtualWidth, views.virtualHeight)).also { addChild(it) }

        val movementAnimator = MovementAnimator()
        addChild(movementAnimator)

        val backpack = BackpackUI().apply {
            xy(40.0, 40.0)
            addTo(this@sceneInit)
        }

        platform = Platform(Rectangle.fromBounds(0, views.virtualHeight * 3/4, views.virtualWidth, views.virtualHeight))
        addChild(platform)

        suspend fun View.putAt(x: Number) {
            if(this is Loadable) initLoad()
            this.x = x.toDouble()
            putOnShelf(platform)
            addTo(this@sceneInit)
        }

        SingleCharObject('A').putAt(80)

        SingleCharObject('T').putAt(120)

        TreeObject("*REE").putAt(200)

        SingleCharObject('E').putAt(400)

        SingleCharObject('L').putAt(500)

        CowObject("COW").putAt(700)

        MapleTreeObject("M*P**").putAt(900)

        Player().apply {
            x = 50.0
            addComponent(StayOnShelfComponent(this, platform))
            addComponent(HorizontalKeyMovementComponent(this))
            addComponent(LetterManipulatorKeyComponent(this, backpack, movementAnimator))
            addComponent(SelectCollidingLetterBoxComponent(this))
            addTo(this@sceneInit)
        }

        // must be in front
        sendChildToFront(movementAnimator)
    }
}