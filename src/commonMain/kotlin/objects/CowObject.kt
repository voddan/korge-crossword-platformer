package objects

import com.soywiz.korim.format.readBitmap
import com.soywiz.korio.file.std.resourcesVfs
import views.DisappearingBitmapView
import views.DisappearingView

class CowObject(val initialWord: String) : AbstractLetterObject("COW") {
    override suspend fun initObjectView(): DisappearingView {
        val sourceImage = resourcesVfs["animals/Cow/walk-left/2-02-00.png"].readBitmap()
        val view = DisappearingBitmapView(sourceImage.toBMP32())
        view.initLoad()
        view.y = -view.height * 0.85
        return view
    }

    override suspend fun initLoad() {
        super.initLoad()
        insertLetters(initialWord)
    }
}

