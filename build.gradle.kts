buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.4.0")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.0")
    }
//    ext {
//        compose_version = "1.2.0"
//        nav_version = "2.5.3"
//        hilt_version = "2.44"
//        retrofit2_version = "2.9.0"
//        gson_version = "2.10.1"
//    }
}// Top-level build file where you can add configuration options common to all sub-projects/modules.

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

plugins {
    id ("com.android.application") version "7.4.0" apply false
    id ("com.android.library") version "7.4.0" apply false
    id ("org.jetbrains.kotlin.android") version "1.7.0" apply false
    id ("com.google.dagger.hilt.android") version "2.44" apply false
}