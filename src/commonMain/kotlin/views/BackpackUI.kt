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
    companion object const {
        const val MIN_WIDTH = 500.0
    }

    override val positionLine: VectorPath = buildPath {
        rLineToH(MIN_WIDTH * 10)
    }
    
    init {
        graphics {
            val rect = solidRect(MIN_WIDTH, 5.0, color = Colors["#c22aff"])
            rect.y = Letter.SIZE
        }
    }

    private val collectedLetters = mutableListOf<Letter>()

    public fun addLetter(letter: Letter) {
        letter.parent?.removeChild(letter)
        letter.x = collectedLetters.size * Letter.SIZE * 1.8
        putOnShelf(letter, this)
        addChild(letter)
        collectedLetters.add(letter)
    }
}