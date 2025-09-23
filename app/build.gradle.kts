@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.com.android.application)
    alias(libs.plugins.org.jetbrains.kotlin.android)
}

android {
    namespace = "ir.rezazarchi.shamsicalendar"
    compileSdk = 35

    defaultConfig {
        applicationId = "ir.rezazarchi.shamsicalendar"
        minSdk = 30
        targetSdk = 33
        versionCode = 2
        versionName = "1.1.0"
        vectorDrawables {
            useSupportLibrary = true
        }

    }

    testOptions {
        unitTests.all {
            it.useJUnitPlatform()
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
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
        kotlinCompilerExtensionVersion = "1.5.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.play.services.wearable)
    implementation(platform(libs.compose.bom))
    implementation(libs.tiles)
    implementation(libs.horologist.tiles)
    implementation(libs.watchface.complications.data.source.ktx)
    implementation(libs.calendar)
    // Use to utilize standard components and layouts in your tiles
    implementation(libs.protolayout)

    // Use to utilize components and layouts with Material Design in your tiles
    implementation(libs.protolayout.material3)

    // Use to include dynamic expressions in your tiles
    implementation(libs.protolayout.expression)

    implementation(libs.guava)

    implementation(libs.gson)

    // Use to preview wear tiles in your own app
    debugImplementation(libs.tiles.renderer)

    // Use to fetch tiles from a tile provider in your tests
    testImplementation(libs.tiles.testing)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.ui.test.junit4)
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)

    testImplementation(libs.bundles.kotest)
}