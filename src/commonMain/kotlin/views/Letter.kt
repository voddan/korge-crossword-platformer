package views

import com.soywiz.klock.milliseconds
import com.soywiz.korge.input.draggable
import com.soywiz.korge.tween.get
import com.soywiz.korge.tween.tween
import com.soywiz.korge.view.*
import com.soywiz.korim.color.Colors
import com.soywiz.korio.async.launchImmediately
import com.soywiz.korma.geom.Point
import com.soywiz.korma.geom.vector.circle


class Letter(val value: Char) : Container() {
    companion object const {
        const val SIZE = 20.0
        val RETURN_TO_POS_TIME = 300.milliseconds
    }

    var state: State = State.PARKED
    enum class State { PARKED, DRAGGED }

    init {
        graphics {
            solidRect(SIZE, SIZE, color = Colors.LIGHTYELLOW)
            text("$value", color = Colors["#c22aff"], textSize = SIZE) {
                pos = Point(0.2, -0.1) * SIZE
            }
        }

        // Debug
        graphics {
            fill(Colors.BLACK) {
                circle(0, 0, 3)
            }
        }


        draggable { drag ->
            if(drag.start) {
                state = State.DRAGGED
            }

            if(drag.end) {
                state = State.PARKED

                val stage = stage ?: return@draggable
                val box = stage.findCollision(this) { it is LetterBox && it != parent } as? LetterBox

                if(box != null) {
                    // Switch to a new LetterBox
                    addTo(box)
                    x = 0.0
                    y = 0.0
                } else {
                    // Return to your LetterBox
                    if(parent is LetterBox) {
                        launchImmediately(stage.views.coroutineContext) {
                            tween(::x[0.0], ::y[0.0], time = RETURN_TO_POS_TIME)
                        }
                    }
                }

            }
        }
    }
}