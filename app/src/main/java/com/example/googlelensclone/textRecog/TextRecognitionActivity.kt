package com.example.googlelensclone.textRecog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.camera.core.ImageAnalysis
import androidx.core.content.ContextCompat
import com.example.googlelensclone.BaseLensActivity

class TextRecognitionActivity : BaseLensActivity() {
    override val imageAnalyzer=TextAnalyzer()

    override fun startScanner() {
        startTextRecognition()
    }
    private fun startTextRecognition(){
        imageAnalysis.setAnalyzer(
            ContextCompat.getMainExecutor(this),
            imageAnalyzer
        )
    }

}