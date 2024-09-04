plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.googleDaggerHiltAndroid)
    id("kotlin-kapt")
    kotlin("plugin.serialization") version "1.9.0"
    id("com.apollographql.apollo") version "4.0.0"
    id("com.google.gms.google-services")

}

android {
    namespace = "com.example.epsswim"
    compileSdk = 34


    defaultConfig {
        applicationId = "com.example.epsswim"
        minSdk = 24
        targetSdk = 34
        versionCode = 2
        versionName = "1.0.1"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            isDebuggable = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("debug")
        }
    }
    compileOptions {
        isCoreLibraryDesugaringEnabled = true

        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

}


dependencies {

    implementation(libs.androidx.runtime.livedata)
    coreLibraryDesugaring ("com.android.tools:desugar_jdk_libs:2.0.4")
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    //Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)
    kapt (libs.androidx.hilt.compiler)
    implementation (libs.androidx.hilt.navigation.compose)
    //Retrofit
    implementation(libs.retrofit2.retrofit)
    implementation (libs.converter.gson)
    //navigation
    implementation(libs.androidx.navigation.compose)
    //serialization
    implementation(libs.kotlinx.serialization.json)
    //room
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)
    //splash screen
    implementation(libs.androidx.core.splashscreen)
    //Coil
    implementation(libs.coil.compose)
    // The compose calendar library for Android
    implementation("com.kizitonwose.calendar:compose:2.4.1")
    //Datastore
    implementation ("androidx.datastore:datastore-preferences:1.1.1")
    //JWTDecoder
    implementation ("com.auth0.android:jwtdecode:2.0.2")
    //Firebase
    implementation(platform("com.google.firebase:firebase-bom:33.1.2"))
    implementation("com.google.firebase:firebase-analytics")
    implementation("com.google.firebase:firebase-storage")
    //Markdown
    implementation("com.mohamedrejeb.richeditor:richeditor-compose:1.0.0-rc06")

}
