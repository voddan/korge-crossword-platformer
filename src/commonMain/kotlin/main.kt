import com.soywiz.korge.Korge
import com.soywiz.korge.scene.Module
import com.soywiz.korge.scene.Scene
import com.soywiz.korim.color.Colors
import com.soywiz.korim.color.RGBA
import com.soywiz.korinject.AsyncInjector
import com.soywiz.korma.geom.SizeInt
import scenes.GameScene
import kotlin.reflect.KClass

suspend fun main() = Korge(Korge.Config(module = MainModule))

object MainModule : Module() {

	override val mainScene: KClass<out Scene> get() = GameScene::class
	override val title: String get() = "Crossword Platformer"
	override val size: SizeInt get() = SizeInt(512, 512)
	override val bgcolor: RGBA get() = Colors.BLACK

	override suspend fun AsyncInjector.configure() {
		mapPrototype { GameScene() }
	}
}