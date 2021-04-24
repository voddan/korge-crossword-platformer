package components

import com.soywiz.klock.TimeSpan
import com.soywiz.korge.component.UpdateComponent
import com.soywiz.korge.view.View

class StayOnShelfComponent(override val view: View, val shelf: HorizontalShelf) : UpdateComponent {
    override fun update(dt: TimeSpan) {
        view.y = shelf.positionY(view.x)
    }
}