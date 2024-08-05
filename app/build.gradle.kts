plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.google.gms.google.services)
    id("androidx.navigation.safeargs.kotlin")

}

android {
    namespace = "com.example.coffeeshopapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.coffeeshopapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        buildConfigField ("String", "GCM_DEFAULT_SENDER_ID", "\"${project.findProperty("GCM_DEFAULT_SENDER_ID")}\"")
        buildConfigField ("String", "GOOGLE_API_KEY", "\"${project.findProperty("GOOGLE_API_KEY")}\"")
        buildConfigField ("String", "GOOGLE_APP_ID", "\"${project.findProperty("GOOGLE_APP_ID")}\"")
        buildConfigField ("String", "GOOGLE_CRASH_REPORTING_API_KEY", "\"${project.findProperty("GOOGLE_CRASH_REPORTING_API_KEY")}\"")
        buildConfigField ("String", "GOOGLE_STORAGE_BUCKET", "\"${project.findProperty("GOOGLE_STORAGE_BUCKET")}\"")
        buildConfigField ("String", "PROJECT_ID", "\"${project.findProperty("PROJECT_ID")}\"")

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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures{
        viewBinding=true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.firebase.database)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    //Lifecycle
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")

    //ViewModel
    implementation("androidx.activity:activity-ktx:1.4.0")

    implementation ("com.github.bumptech.glide:glide:4.12.0")
    implementation ("com.google.code.gson:gson:2.9.1")
    //navigation
    val nav_version = "2.7.7"
    implementation("androidx.navigation:navigation-fragment-ktx:$nav_version")
    implementation("androidx.navigation:navigation-ui-ktx:$nav_version")

}