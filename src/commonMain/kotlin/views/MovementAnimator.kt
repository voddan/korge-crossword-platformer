package views

import com.soywiz.klock.seconds
import com.soywiz.korge.tween.get
import com.soywiz.korge.tween.tween
import com.soywiz.korge.view.Container
import com.soywiz.korge.view.View
import com.soywiz.korio.async.launchImmediately
import com.soywiz.korma.geom.IPoint
import com.soywiz.korma.geom.Point
import kotlinx.coroutines.Dispatchers

class MovementAnimator : Container() {
    public fun moveViewTo(view: View, globalPos: IPoint, doAfter: (View) -> Unit = {}) {
        val oldPos = view.globalPos
        addChild(view)

        launchImmediately(Dispatchers.Default) {
            view.tween(
                    view::globalX[oldPos.x, globalPos.x],
                    view::globalY[oldPos.y, globalPos.y],
                    time = 1.seconds)

            doAfter(view)
        }
    }
}

fun MovementAnimator.swapViews(view1: View, view2: View, doAfterEach: (View) -> Unit = {}) {
    moveViewTo(view1, view2.globalPos, doAfterEach)
    moveViewTo(view2, view1.globalPos, doAfterEach)
}

fun MovementAnimator.moveViewToParent(view: View, parent: Container, doAfter: (View) -> Unit = {}) {
    moveViewTo(view, parent.globalPos) {
        parent.addChild(view)
        view.pos = Point(0.0, 0.0)
        doAfter(view)
    }
}

val View.globalPos: Point get() = localToGlobal(Point.Zero)