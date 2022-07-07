plugins {
    id("com.android.application")
    id("dagger.hilt.android.plugin")
    id("kotlin-parcelize")
    id("androidx.navigation.safeargs")

    kotlin("android")
    kotlin("kapt")
    kotlin("plugin.serialization") version "1.6.21"
}

android {
    compileSdk = 32

    defaultConfig {
        applicationId = "com.gabin.newsreader"
        minSdk = 23
        targetSdk = 32
        versionCode = 1
        versionName = "0.0.1"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
            isDebuggable = false
        }
        getByName("debug") {
            buildConfigField("String", "NEWS_API_KEY", "\"b2e3c1188668489aabcad2ff307c4054\"")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    packagingOptions {
        resources.excludes += "/META-INF/{AL2.0,LGPL2.1}"
    }
    buildFeatures {
        viewBinding = true
    }
}
kapt {
    correctErrorTypes = true
}

dependencies {

    val hiltVersion = "2.42"
    implementation("com.google.dagger:hilt-android:$hiltVersion")
    kapt("com.google.dagger:hilt-android-compiler:$hiltVersion")

    implementation("androidx.core:core-ktx:1.8.0")
    implementation("androidx.fragment:fragment-ktx:1.5.0")
    implementation("androidx.appcompat:appcompat:1.4.2")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    val navVersion = "2.5.0"
    implementation("androidx.navigation:navigation-fragment-ktx:$navVersion")
    implementation("androidx.navigation:navigation-ui-ktx:$navVersion")


    val coroutinesVersion = "1.6.3"
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutinesVersion")

    val ktorVersion = "2.0.2"
    implementation("io.ktor:ktor-client-core:$ktorVersion")
    implementation("io.ktor:ktor-client-okhttp:$ktorVersion")
    implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
    testImplementation("io.ktor:ktor-client-mock:$ktorVersion")

    implementation("com.google.android.material:material:1.6.1")
    implementation("io.coil-kt:coil:2.1.0")
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}
