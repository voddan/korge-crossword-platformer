package objects

import com.soywiz.korim.format.readBitmap
import com.soywiz.korio.file.std.resourcesVfs
import views.DisappearingBitmapView
import views.DisappearingView

class MapleTreeObject(val initialWord: String) : AbstractLetterObject("MAPLE") {
    override suspend fun initObjectView(): DisappearingView {
        val sourceImage = resourcesVfs["trees/Frame/Big/-_tree_4_big_4.png"].readBitmap()
        val view = DisappearingBitmapView(sourceImage.toBMP32())
        view.initLoad()
        view.y = -view.height * 0.9
        return view
    }

    override suspend fun initLoad() {
        super.initLoad()
        insertLetters(initialWord)
    }
}

