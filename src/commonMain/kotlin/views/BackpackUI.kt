package views

import com.soywiz.korge.view.Container
import com.soywiz.korge.view.graphics
import com.soywiz.korge.view.solidRect
import com.soywiz.korim.color.Colors
import com.soywiz.korma.geom.shape.buildPath
import com.soywiz.korma.geom.vector.VectorPath
import com.soywiz.korma.geom.vector.rLineToH
import components.HorizontalShelf
import components.putOnShelf

class BackpackUI : Container(), HorizontalShelf {
    val minWidth = 500.0

    override val positionLine: VectorPath = buildPath {
        rLineToH(minWidth * 10)
    }
    
    init {
        graphics {
            val rect = solidRect(minWidth, 5.0, color = Colors["#c22aff"])
            rect.y = 20.0
        }
    }

    private val collectedLetters = mutableListOf<Letter>()

    public fun addLetter(letter: Letter) {
        letter.parent?.removeChild(letter)
        letter.x = collectedLetters.size * letter.width * 1.8
        putOnShelf(letter, this)
        addChild(letter)
        collectedLetters.add(letter)
    }
}