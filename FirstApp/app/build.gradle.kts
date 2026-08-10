plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.compose.compiler)
    id("androidx.navigation.safeargs.kotlin")
    id("kotlin-parcelize")
}

android {
    namespace = "com.example.week2amantasksxml"

    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "com.example.week2amantasksxml"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        viewBinding = true
        compose = true
    }
}

dependencies {

    // Core
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)

    // UI
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.recyclerview)

    // Navigation
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.navigation.ui)

    // Compose
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui.tooling.preview)

    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)

    // Unit Tests
    testImplementation(libs.junit)

    // Instrumentation Tests
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)

    // Coil
    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)

    //Navigation
    implementation(libs.androidx.navigation.compose)


    //LiveData
    implementation("androidx.compose.runtime:runtime-livedata")


}