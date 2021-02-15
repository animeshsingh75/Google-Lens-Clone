package com.example.googlelensclone.facedetect

import android.annotation.SuppressLint
import android.media.FaceDetector
import android.util.Log
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.face.FaceDetection
import com.google.mlkit.vision.face.FaceDetectorOptions

class FaceDetectAnalyser : ImageAnalysis.Analyzer {
    val detector = FaceDetection.getClient(
        FaceDetectorOptions.Builder()
            .setPerformanceMode(FaceDetectorOptions.PERFORMANCE_MODE_ACCURATE)
            .setLandmarkMode(FaceDetectorOptions.LANDMARK_MODE_ALL)
            .setClassificationMode(FaceDetectorOptions.CLASSIFICATION_MODE_ALL)
            .build()
    )

    @SuppressLint("UnsafeExperimentalUsageError")
    override fun analyze(imageProxy: ImageProxy) {
        Log.wtf("FACEDETECT", "image analyzed")

        imageProxy.image?.let {
            val inputImage = InputImage.fromMediaImage(
                it,
                imageProxy.imageInfo.rotationDegrees
            )
            detector.process(inputImage)
                .addOnSuccessListener { faces ->
                    Log.wtf("FACEDETECT", "Faces=${faces.size}")
                    faces.forEach {
                        Log.wtf(
                            "FACEDETECT", """
                            leftEye=${it.leftEyeOpenProbability}
                            rightEye=${it.rightEyeOpenProbability}    
                            smile=${it.smilingProbability}
                        """.trimIndent()
                        )
                    }
                }
                .addOnFailureListener { ex ->
                    Log.e("FACEDETECT", "Detection Failed", ex)
                }
                .addOnCompleteListener {
                    imageProxy.close()
                }
        } ?: imageProxy.close()
    }
}