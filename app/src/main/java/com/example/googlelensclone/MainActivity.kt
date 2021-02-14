package com.example.googlelensclone

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Matrix
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import com.example.googlelensclone.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    companion object{
        @JvmStatic val PHOTO_REQ_CODE=234
        @JvmStatic val EXTRA_DATA="data"
    }
    lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.btnTakeExtPhoto.setOnClickListener {
            val takePhotoIntent=Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(takePhotoIntent, PHOTO_REQ_CODE)
        }
        binding.btnCameraActivity.setOnClickListener {
            startActivity(Intent(this, CameraActivity::class.java))
        }
    }
    fun rotate(bitmap:Bitmap):Bitmap{
        val matrix = Matrix()
        matrix.postRotate(90f)
        val scaledBitmap = Bitmap.createScaledBitmap(bitmap, bitmap.height, bitmap.width, true)
        return Bitmap.createBitmap(
                scaledBitmap,
                0,
                0,
                scaledBitmap.width,
                scaledBitmap.height,
                matrix,
                true
        )
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode== PHOTO_REQ_CODE){
            val bitmap=data?.extras?.get(EXTRA_DATA) as Bitmap
            val rotatedBitmap=rotate(bitmap)
            binding.imgThumb.setImageBitmap(rotatedBitmap)
            return
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}