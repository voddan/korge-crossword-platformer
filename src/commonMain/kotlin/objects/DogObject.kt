package objects

import com.soywiz.korim.format.readBitmap
import com.soywiz.korio.file.std.resourcesVfs
import views.DisappearingBitmapView
import views.DisappearingView

class DogObject(val initialWord: String) : AbstractLetterObject("DOG") {
    override suspend fun initObjectView(): DisappearingView {
        val sourceImage = resourcesVfs["animals/Dog/tail/idle2/corgi-asset-01-02.png"].readBitmap()
        val view = DisappearingBitmapView(sourceImage.flipX().toBMP32())
        view.initLoad()
        view.scale = 1.5
        view.y = -view.scaledHeight
        return view
    }

    override suspend fun initLoad() {
        super.initLoad()
        insertLetters(initialWord)
    }
}

