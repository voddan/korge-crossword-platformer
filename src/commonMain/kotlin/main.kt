import com.soywiz.korge.Korge
import com.soywiz.korge.scene.Module
import com.soywiz.korge.scene.Scene
import com.soywiz.korim.color.Colors
import com.soywiz.korim.color.RGBA
import com.soywiz.korinject.AsyncInjector
import scenes.GameScene
import kotlin.reflect.KClass

suspend fun main() = Korge(Korge.Config(module = MainModule))

object MainModule : Module() {

	override val mainScene: KClass<out Scene> get() = GameScene::class
	override val title: String get() = "Crossword Platformer"
	override val bgcolor: RGBA get() = Colors.BLACK

	override suspend fun AsyncInjector.configure() {
		mapPrototype { GameScene() }
	}
}