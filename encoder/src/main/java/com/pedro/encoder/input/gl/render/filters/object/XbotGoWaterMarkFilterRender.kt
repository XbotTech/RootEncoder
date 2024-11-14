package com.pedro.encoder.input.gl.render.filters.`object`

import android.graphics.Bitmap
import android.opengl.GLES20
import android.os.Build
import androidx.annotation.RequiresApi
import com.pedro.encoder.utils.gl.ImageStreamObject


@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
class XbotGoWaterMarkFilterRender : BaseObjectFilterRender() {
    var ignore: Boolean = false

    init {
        streamObject = ImageStreamObject()
    }

    override fun drawFilter() {
        super.drawFilter()
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, streamObjectTextureId[0])
        //Set alpha. 0f if no image loaded.
        GLES20.glUniform1f(
            uAlphaHandle,
            if (ignore || (streamObjectTextureId[0] == -1)) 0f else alpha
        )
    }

    fun setImage(bitmap: Bitmap?) {
        (streamObject as ImageStreamObject).load(bitmap)
        shouldLoad = true
    }
}
