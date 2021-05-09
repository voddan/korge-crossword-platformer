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

class TranslateViewAnimator : Container() {
    public fun translateViewTo(view: View, globalPos: IPoint, doAfter: (View) -> Unit = {}) {
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

fun TranslateViewAnimator.translateViewToParent(
        view: View,
        parent: Container,
        dPos: Point = Point(0.0, 0.0),
        doAfter: (View) -> Unit = {}
) {
    translateViewTo(view, parent.localToGlobal(dPos)) {
        parent.addChild(view)
        view.pos = dPos
        doAfter(view)
    }
}

val View.globalPos: Point get() = localToGlobal(Point.Zero)