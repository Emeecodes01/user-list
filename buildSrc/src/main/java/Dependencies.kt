import org.gradle.api.artifacts.dsl.DependencyHandler

const val kotlinVersion = "1.4.0"
const val gradle = "4.1.1"

object Config {
    object Versions {
        const val compileSdkVer = 29
        const val buildToolsVer = "30.0.0"
        const val minSdk = 21
        const val targetSdk = 29
        const val versionCode = 1
        const val versionName = "v1.0.0"
    }

    object Android {
        const val applicationId = "ng.softcom.databeaver"
        const val testRunner = "androidx.test.runner.AndroidJUnitRunner"
    }


    object ClassPaths {
        const val androidGradlePlugin = "com.android.tools.build:gradle:$gradle"
        const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
        const val safeArgs = "androidx.navigation:navigation-safe-args-gradle-plugin:${Dependencies.Navigation.Version.navVersion}"
        const val daggerHilt = "com.google.dagger:hilt-android-gradle-plugin:${Dependencies.DependencyInjection.Version.daggerHiltVersion}"
        const val spotless = "com.diffplug.spotless:spotless-plugin-gradle:${Dependencies.Lint.Version.spotless}"
    }

}


object Dependencies {

    object AndroidX {
        object Versions {
            const val coreKtx = "1.3.2"
            const val appCompact = "1.2.0"
            const val activityExt = "1.2.0-beta01"
            const val fragmentExt = "1.3.0-beta01"
        }

        const val core = "androidx.core:core-ktx:${Versions.coreKtx}"
        const val appCompact = "androidx.appcompat:appcompat:${Versions.appCompact}"
        const val kotlinStdlib = "org.jetbrains.kotlin:kotlin-stdlib:${kotlinVersion}"
        const val activityExtLib = "androidx.activity:activity-ktx:${Versions.activityExt}"
        const val fragmentExt = "androidx.fragment:fragment-ktx:${Versions.fragmentExt}"
    }


    object UILibs {
        object Versions {
            const val materialDesignComps = "1.2.1"
            const val constraintLayout = "2.0.4"
            const val progressButton = "3.0.3"
            const val loadingView = "1.4.0"
            const val pagerIndicator = "1.0.6"
            const val aviLoadingVersion = "2.1.3"
            const val philChartLib = "v3.1.0"
            const val customRadioLib = "v2.1.1"
        }
        const val materialDesign = "com.google.android.material:material:${Versions.materialDesignComps}"
        const val constraintLayoutDep = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
        const val progressButtonLib = "com.mikhaellopez:circularprogressbar:${Versions.progressButton}"
        const val loadingView = "com.github.ybq:Android-SpinKit:${Versions.loadingView}"
        const val pagerIndicator = "com.github.adrielcafe:PageIndicatorView:${Versions.pagerIndicator}"
        const val aviLoading = "com.wang.avi:library:${Versions.aviLoadingVersion}"
        const val philChartLib = "com.github.PhilJay:MPAndroidChart:${Versions.philChartLib}"
        const val radioRealButton = "com.github.ceryle:RadioRealButton:${Versions.customRadioLib}"
    }


    object Persistence {
        object Versions {
            const val roomVersion = "2.2.5"
        }

        const val room = "androidx.room:room-runtime:${Versions.roomVersion}"
        const val roomCompiler = "androidx.room:room-compiler:${Versions.roomVersion}"
        const val roomExt = "androidx.room:room-ktx:${Versions.roomVersion}"
    }


    object DependencyInjection {
        object Version {
            const val daggerHiltVersion = "2.28-alpha"
        }

        const val daggerHilt = "com.google.dagger:hilt-android:${Version.daggerHiltVersion}"
        const val daggerCompiler = "com.google.dagger:hilt-android-compiler:${Version.daggerHiltVersion}"
    }


    object Async {
        object Version {
            const val coroutines = "1.4.0"
        }
        const val ktCoroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Version.coroutines}"
        const val ktCoroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.coroutines}"
    }

    object Architecture {
        object Version {
            const val viewModelVersion = "2.2.0"
        }

        const val viewModel = "androidx.lifecycle:lifecycle-viewmodel:${Version.viewModelVersion}"
        const val viewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Version.viewModelVersion}"
    }


    object Navigation {
        object Version {
            const val navVersion = "2.3.1"
        }

        const val navFragment = "androidx.navigation:navigation-fragment-ktx:${Version.navVersion}"
        const val navUI = "androidx.navigation:navigation-ui-ktx:${Version.navVersion}"
        const val navFeatureModule = "androidx.navigation:navigation-dynamic-features-fragment:${Version.navVersion}"
    }

    object Api {
        object Version {
            const val retrofit = "2.9.0"
            const val okhttpInterceptor = "4.9.0"
            const val gson = "2.8.6"
            const val gsonConverter = "2.9.0"
        }

        const val retrofit = "com.squareup.retrofit2:retrofit:${Version.retrofit}"
        const val okhttpInterceptor = "com.squareup.okhttp3:logging-interceptor:${Version.okhttpInterceptor}"
        const val gson = "com.google.code.gson:gson:${Version.gson}"
        const val gsonConverter = "com.squareup.retrofit2:converter-gson:${Version.gsonConverter}"
    }

    object Lint {
        object Version {
            const val spotless = "3.27.0"
        }
    }

}


object TestDependencies {

    object Versions {
        const val junitVersion = "4.13.1"
        const val androidJunit = "1.1.2"
        const val espressoCore = "3.3.0"
        const val annotation = "1.1.0"
        const val konveyor = "1.0.3"
    }

    const val junit = "junit:junit:${Versions.junitVersion}"
    const val androidJunit = "androidx.test.ext:junit:${Versions.androidJunit}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
    const val annotation = "androidx.annotation:annotation:${Versions.annotation}"
    const val konveyor = "com.github.vacxe:konveyor:${Versions.konveyor}"
}


fun DependencyHandler.domain() {
    add("implementation", Dependencies.AndroidX.kotlinStdlib)
    add("implementation", Dependencies.Async.ktCoroutines)
    add("implementation", Dependencies.Async.ktCoroutinesAndroid)
    dagger()
    unitTestDeps()
}


fun DependencyHandler.archComponent() {
    add("implementation", Dependencies.Architecture.viewModel)
    add("implementation", Dependencies.Architecture.viewModelKtx)
}

fun DependencyHandler.navigationComponent() {
    add("api", Dependencies.Navigation.navFragment)
    add("api", Dependencies.Navigation.navUI)
    add("api", Dependencies.Navigation.navFeatureModule)
}

fun DependencyHandler.unitTestDeps() {
    add("testImplementation", TestDependencies.junit)
    add("testImplementation", TestDependencies.konveyor)
}


fun DependencyHandler.androidUITestDeps() {
    add("androidTestImplementation", TestDependencies.androidJunit)
    add("androidTestImplementation", TestDependencies.espresso)
    add("androidTestImplementation", TestDependencies.annotation)
}


fun DependencyHandler.androidX(){
    add("implementation", Dependencies.AndroidX.appCompact)
    add("implementation", Dependencies.AndroidX.core)
    add("implementation", Dependencies.AndroidX.activityExtLib)
}


fun DependencyHandler.dagger() {
    add("api", Dependencies.DependencyInjection.daggerHilt)
    add("kapt", Dependencies.DependencyInjection.daggerCompiler)
}