import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdkVersion(Config.Versions.compileSdkVer)
    buildToolsVersion(Config.Versions.buildToolsVer)

    defaultConfig {
        minSdkVersion(Config.Versions.minSdk)
        targetSdkVersion(Config.Versions.targetSdk)
        versionCode = Config.Versions.versionCode
        versionName = Config.Versions.versionName

        testInstrumentationRunner = Config.Android.testRunner
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        named("release") {
            isMinifyEnabled = false
            buildConfigField("String", "BASE_URL", "\"https://dummyapi.io/\"")
            buildConfigField("String", "APP_ID", "\"600ac8b364266e3d0faa1036\"")
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-logic.pro")
        }

        named("debug") {
            isMinifyEnabled = false
            buildConfigField("String", "BASE_URL", "\"https://dummyapi.io/\"")
            buildConfigField("String", "APP_ID", "\"600ac8b364266e3d0faa1036\"")
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
    remote()
}