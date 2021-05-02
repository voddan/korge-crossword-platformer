package objects

import com.soywiz.korim.format.readBitmap
import com.soywiz.korio.file.std.resourcesVfs
import views.DisappearingBitmapView
import views.DisappearingView

class TreeObject(val initialWord: String) : AbstractLetterObject("TREE") {
    override suspend fun initObjectView(): DisappearingView {
        val sourceImage = resourcesVfs["trees/Frame/Big/-_tree_1_big_0.png"].readBitmap()
        val view = DisappearingBitmapView(sourceImage.toBMP32())
        view.initLoad()
        view.y = -view.height * 0.9
        view.scaleX = 0.6
        return view
    }

    override suspend fun initLoad() {
        super.initLoad()
        insertLetters(initialWord)
    }
}

