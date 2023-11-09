 # LyticalSpoof Android SDK

Welcome to the LyticalSpoof Android SDK! This SDK allows you to integrate face liveness detection into your Android application, ensuring a secure and reliable user authentication process.

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

## Step 3: Initialize LyticalSpoof

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


