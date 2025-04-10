plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    //alias(libs.plugins.compose.compiler)
    id("com.google.gms.google-services") version "4.4.2" apply false

}

android {
    namespace = "alcala.jose.practica10"
    compileSdk = 35

    defaultConfig {
        applicationId = "alcala.jose.practica10"
        minSdk = 21
        targetSdk = 35
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
    //composeOptions {
      //  kotlinCompilerExtensionVersion = "1.5.3"  // Usa la versión compatible
    //}
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.runtime.android)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    //firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.auth)
    //Google
    implementation(libs.androidx.credentials)
    implementation(libs.androidx.credentials.play.services.auth)
    //Compose
    implementation(libs.androidx.ui.android)
    implementation(libs.androidx.activity.compose)

}