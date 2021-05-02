package views

import com.soywiz.korim.format.readBitmap
import com.soywiz.korio.file.std.resourcesVfs
import models.DisappearingBitmapView
import models.DisappearingView

class TreeObject(val initialWord: String) : LetterObject("TREE") {
    override suspend fun initObjectView(): DisappearingView {
        val sourceImage = resourcesVfs["trees/Frame/Big/-_tree_1_big_0.png"].readBitmap()
        val view = DisappearingBitmapView(sourceImage.toBMP32())
        view.initLoad()
        view.y = -view.height
        return view
    }

    override suspend fun initLoad() {
        super.initLoad()
        insertLetters(initialWord)
    }
}

