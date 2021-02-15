package com.example.googlelensclone.faceDetect

import androidx.core.content.ContextCompat
import com.example.googlelensclone.BaseLensActivity

class FaceDetectActivity : BaseLensActivity() {
    override val imageAnalyzer= FaceDetectAnalyser()

    override fun startScanner() {
        startFaceDetect()
    }

    private fun startFaceDetect() {
        imageAnalysis.setAnalyzer(
            ContextCompat.getMainExecutor(this),
            imageAnalyzer
        )
    }
}