@file:Suppress("UnstableApiUsage")

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.lfgtavora.pokecards"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.lfgtavora.pokecards"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        val debug by getting {
            applicationIdSuffix = ".debug"
        }
        val release by getting {
            {
                isMinifyEnabled = true
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
        buildFeatures {
            compose = true
        }
        composeOptions {
            kotlinCompilerExtensionVersion = "1.4.3"
        }
        packagingOptions {
            resources {
                exclude("/META-INF/{AL2.0,LGPL2.1}")
            }
        }
    }

    dependencies {

        implementation(project(":paging"))


        //Android framework
        implementation("androidx.core:core-ktx:1.7.0")
        implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.3.1")
        //implementation 'androidx.lifecycle:lifecycle-runtime-compose:2.5.1'
        implementation("androidx.activity:activity-compose:1.3.1")
        implementation("androidx.compose.ui:ui:1.2.0")
        implementation("androidx.compose.ui:ui-tooling-preview:1.2.0")
        implementation("androidx.compose.material3:material3:1.0.0-alpha11")
        implementation("androidx.navigation:navigation-compose:2.5.3")
        implementation("androidx.hilt:hilt-navigation-compose:1.0.0")

        //IMAGE
        implementation("io.coil-kt:coil-compose:2.2.2")

        //DI
        implementation("com.google.dagger:hilt-android:2.44")
        kapt("com.google.dagger:hilt-compiler:2.44")

        //NETWORKING
        implementation("com.squareup.retrofit2:retrofit:2.9.0")
        implementation("com.squareup.retrofit2:converter-gson:2.9.0")
        implementation("com.google.code.gson:gson:2.10.1")


        //Room
        val roomVersion = "2.5.1"
        implementation("androidx.room:room-runtime:$roomVersion")
        implementation("androidx.room:room-ktx:$roomVersion")
        implementation("androidx.room:room-paging:$roomVersion")
        kapt("androidx.room:room-compiler:$roomVersion")

        //testing
        testImplementation("junit:junit:4.13.2")
        androidTestImplementation("androidx.test.ext:junit:1.1.3")
        androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
        androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.2.0")
        debugImplementation("androidx.compose.ui:ui-tooling:1.2.0")
        debugImplementation("androidx.compose.ui:ui-test-manifest:1.2.0")
        testImplementation("org.amshove.kluent:kluent-android:1.73")
    }
}
dependencies {
    testImplementation("junit:junit:4.12")
}
