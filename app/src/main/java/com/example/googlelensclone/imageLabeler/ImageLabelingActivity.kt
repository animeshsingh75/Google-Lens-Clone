package com.example.googlelensclone.imageLabeler

import androidx.camera.core.ImageAnalysis
import androidx.core.content.ContextCompat
import com.example.googlelensclone.BaseLensActivity

class ImageLabelingActivity:BaseLensActivity() {
    override val imageAnalyzer=ImageLabelAnalyzer()

    override fun startScanner() {
        startImageLabelling()
    }
    private fun startImageLabelling(){
        imageAnalysis.setAnalyzer(
            ContextCompat.getMainExecutor(this),
            imageAnalyzer
        )
    }

}