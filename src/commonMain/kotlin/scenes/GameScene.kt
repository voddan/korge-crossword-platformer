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
import objects.DogObject
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

        var lastPosX = 0

        suspend fun View.putAt(x: Int) {
            if(this is Loadable) initLoad()
            lastPosX = x
            this.x = x.toDouble()
            putOnShelf(platform)
            addTo(this@sceneInit)
        }

        suspend fun View.putAtR(relativeX: Int) {
            lastPosX += relativeX
            putAt(lastPosX)
        }

        DogObject("DOG").putAtR(80)

        SingleCharObject('T').putAtR(200)

        TreeObject("*REE").putAtR(60)

        SingleCharObject('W').putAtR(200)
        SingleCharObject('C').putAtR(50)

        CowObject("*O*").putAtR(80)

        MapleTreeObject("M*P**").putAtR(200)

        SingleCharObject('A').putAtR(300)
        SingleCharObject('L').putAtR(50)


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