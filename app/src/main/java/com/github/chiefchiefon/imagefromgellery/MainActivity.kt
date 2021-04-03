package com.github.chiefchiefon.imagefromgellery

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    companion object {
        private const val IMAGE_REQUEST_CODE = 2123
    }

    private val loadImage by lazy { findViewById<Button>(R.id.loadImage) }
    private val imageView by lazy { findViewById<ImageView>(R.id.imageView) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadImage.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, IMAGE_REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (RESULT_OK == resultCode) {
            when (requestCode) {
                IMAGE_REQUEST_CODE -> onImageSelected(data)
            }
        }
    }

    private fun onImageSelected(data: Intent?) {
        data?.let {
            imageView.setImageURI(data?.data)
        }
    }
}