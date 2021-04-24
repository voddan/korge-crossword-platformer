package components

import com.soywiz.klock.TimeSpan
import com.soywiz.korge.component.UpdateComponent
import com.soywiz.korge.view.View
import views.Platform

class StayOnTopComponent(override val view: View, val onToOf: Platform) : UpdateComponent {
    override fun update(dt: TimeSpan) {
        view.y = onToOf.topPositionY(view.x)
    }
}