import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id ("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

android {
    buildToolsVersion(Config.Versions.buildToolsVer)
    compileSdkVersion(Config.Versions.compileSdkVer)

    buildFeatures {
        dataBinding = true
    }

    defaultConfig {
        minSdkVersion(Config.Versions.minSdk)
        targetSdkVersion(Config.Versions.targetSdk)
        compileSdkVersion(Config.Versions.compileSdkVer)
        versionCode =  Config.Versions.versionCode
        versionName = Config.Versions.versionName
        testInstrumentationRunner = Config.Android.testRunner

        javaCompileOptions {
            annotationProcessorOptions {

                arguments(
                    mapOf(
                        "room.schemaLocation" to "$projectDir/schemas",
                        "room.incremental" to "true"
                    )
                )
            }
        }
    }

    buildTypes {
        named("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-logic.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    tasks.withType <KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
    }
}

dependencies {
    cache()
}