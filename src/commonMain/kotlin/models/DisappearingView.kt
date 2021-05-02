package models

import com.soywiz.korge.view.Container
import com.soywiz.korge.view.View
import kotlin.properties.Delegates

abstract class DisappearingView : Container(), Loadable {
    var transparency: Double by Delegates.observable(0.0) {_, _, new ->
        graphics?.alpha = 1 - new
        contours?.alpha = new
    }

    abstract suspend fun initGraphics(): View
    abstract suspend fun initContours(graphics: View): View

    private var graphics: View? = null
    private var contours: View? = null

    override suspend fun initLoad() {
        graphics = initGraphics()
        contours = initContours(graphics!!)

        addChild(graphics!!)
        addChild(contours!!)
    }
}