package scenes

import com.soywiz.klock.seconds
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.tween.get
import com.soywiz.korge.tween.tween
import com.soywiz.korge.view.*
import com.soywiz.korim.format.readBitmap
import com.soywiz.korio.async.launchImmediately
import com.soywiz.korio.file.std.resourcesVfs
import com.soywiz.korma.geom.degrees
import com.soywiz.korma.interpolation.Easing
import views.Player

class GameScene() : Scene() {
    lateinit var player: Player
    lateinit var background: Image

    override suspend fun Container.sceneInit() {
        val minDegrees = (-16).degrees
        val maxDegrees = (+16).degrees

        background = image(resourcesVfs["korge.png"].readBitmap()) {
            rotation = maxDegrees
            anchor(.5, .5)
            scale(.8)
            position(256, 256)
        }

        launchImmediately {
            while(true) {
                background.tween(background::rotation[minDegrees], time = 0.5.seconds, easing = Easing.EASE_IN_OUT)
                background.tween(background::rotation[maxDegrees], time = 1.seconds, easing = Easing.EASE_IN_OUT)
            }
        }

        player = Player()
        addChild(player)
    }
}
