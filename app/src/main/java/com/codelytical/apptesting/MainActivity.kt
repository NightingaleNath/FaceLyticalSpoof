package com.codelytical.apptesting

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.mv.engine.LivenessCameraLibrary
import com.mv.engine.constants.LIVENESS_CAMERA_REQUEST_CODE
import com.mv.engine.enums.SetEncodedKeyResult

@RequiresApi(Build.VERSION_CODES.O)
class MainActivity : AppCompatActivity() {

    private var capturedImage: ImageView? = null
    private lateinit var startCameraX: Button
    private var livenessCameraLibrary: LivenessCameraLibrary? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        livenessCameraLibrary = LivenessCameraLibrary(this)

        val encodedKey = "Y29tLmNvZGVseXRpY2FsLmFwcHRlc3Rpbmd8NiUxRjg5JFJrZjFVITV0R3pBcVh5I29aS3MyKjNuV2J8eHdsdUhyU2xmZ2lmbHM3bXxDb2RlTHl0aWNhbA=="
        livenessCameraLibrary?.setEncodedKey(encodedKey, applicationContext.packageName) { result ->
            when (result) {
                is SetEncodedKeyResult.Success -> {
                    val successMessage = result.message
                    showToast(successMessage)
                }
                is SetEncodedKeyResult.Failure -> {
                    showToast(result.message)
                    Log.e("LivenessCameraLibrary", result.message)
                }
                is SetEncodedKeyResult.Error -> {
                    showToast(result.message)
                    Log.e("LivenessCameraLibrary", result.message)
                }

            }
        }

        capturedImage = findViewById(R.id.capturedImage)
        startCameraX = findViewById(R.id.startCameraX)

        startCameraX.setOnClickListener {
            livenessCameraLibrary?.launchLivenessCamera(3000, true) { capturedImageUri ->
                if (capturedImageUri != null) {
                    val bitmap = livenessCameraLibrary?.uriToBitmap(capturedImageUri)
                    capturedImage?.setImageURI(capturedImageUri)
                    if (bitmap != null) {
                        val base64String = livenessCameraLibrary?.bitmapToBase64(bitmap)
                        if (base64String != null) {
                            // Now you have the image in base64String
                            Log.e("TAG", "base64String encoding bitmap to base64 $base64String")
                        } else {
                            // Handle base64 encoding error
                            Log.e("TAG", "Error encoding bitmap to base64")
                        }
                    } else {
                        // Handle bitmap conversion error
                        Log.e("TAG", "Error converting URI to Bitmap")
                    }
                } else {
                    Log.d("TAG", "onCreate: Nothing")
                }
            }
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == LIVENESS_CAMERA_REQUEST_CODE) {
            livenessCameraLibrary?.handleCaptureResult(resultCode, data)
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}