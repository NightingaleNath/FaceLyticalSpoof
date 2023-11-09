 # LyticalSpoof Android SDK

Welcome to the LyticalSpoof Android SDK! This SDK allows you to integrate face liveness detection into your Android application, ensuring a secure and reliable user authentication process.

Robust, Realtime, On-Device Face Liveness Detection

[![Website](https://img.shields.io/badge/Website-https://kpro.netlify.app/-blue)](https://kpro.netlify.app/)
[![YouTube](https://img.shields.io/badge/YouTube-Codelytical-red)](https://www.youtube.com/@codelytical)

## Introduction

LyticalSpoof provides robust, real-time, on-device face liveness detection for your applications. 

## Getting Started

To use LyticalSpoof in your project, follow these steps:

### Step 1: Obtain LyticalSpoof SDK

1. Contact CodeLytical to obtain the LyticalSpoof SDK.
2. Place the `lyticalspoof.aar` file in the `libs` directory of your Android project.

### Step 2: Set Up Dependencies

Add the following dependencies to your `build.gradle` file:

```groovy
implementation "androidx.camera:camera-camera2:1.3.0"
implementation "androidx.camera:camera-lifecycle:1.3.0"
implementation "androidx.camera:camera-view:1.3.0"

buildFeatures {
    viewBinding = true
    dataBinding = true
}

id 'kotlin-kapt'
```

### Step 2b: Getting Your Key

Contact CodeLytical to obtain the encoded key for your project. This key is required for initializing the library.

### Step 3: Initialize LyticalSpoof

```kotlin
private var livenessCameraLibrary: LivenessCameraLibrary? = null
private var capturedImage: ImageView? = null
private lateinit var startCameraX: Button

// Initialize the library
livenessCameraLibrary = LivenessCameraLibrary(this)

// Set your activation key
val encodedKey = "your_encoded_key_here"

livenessCameraLibrary?.setEncodedKey(encodedKey, applicationContext.packageName) { result ->
    when (result) {
        is SetEncodedKeyResult.Success -> {
            showToast(result.message)
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

```
### Step 4: Launch LyticalSpoof

```
capturedImage = findViewById(R.id.capturedImage)
startCameraX = findViewById(R.id.startCameraX)

// Launch LyticalSpoof
startCameraX.setOnClickListener {
    livenessCameraLibrary?.launchLivenessCamera(3000, true) { capturedImageUri ->
        if (capturedImageUri != null) {
            val bitmap = livenessCameraLibrary?.uriToBitmap(capturedImageUri)
            capturedImage?.setImageURI(capturedImageUri)
            // If you want to use base64String of the captured face
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
            // Handle the captured image as needed
        } else {
            Log.d("TAG", "onCreate: Nothing")
        }
    }
}
```

### Step 5: Handle Activity Result
```
override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)
    if (requestCode == LIVENESS_CAMERA_REQUEST_CODE) {
        livenessCameraLibrary?.handleCaptureResult(resultCode, data)
    }
}
```
## Additional Information

- For more details and customization options, contact CodeLytical for any support or inquiries.

## Youtube

- Check out our [CodeLytical](https://www.youtube.com/@codelytical) for video tutorials and updates.

## Contact Us

For any inquiries, assistance, or support, feel free to reach out to us:

- **Email:** [codehacker768@gmail.com](mailto:codehacker768@gmail.com)
- **WhatsApp:** [+233248865955](https://wa.me/233248865955)
- **Skype:** [live:nath.frema2](skype:live:nath.frema2)

We're here to help you with any questions you may have!


