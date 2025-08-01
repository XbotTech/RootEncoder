plugins {
  id("com.android.library")
  id("org.jetbrains.kotlin.android")
  id(libs.plugins.maven.publish.get().pluginId)
  id("org.jetbrains.dokka")
}

android {
  namespace = "com.pedro.library"
  compileSdk = 36

  defaultConfig {
    minSdk = 16
    lint.targetSdk = 36
  }
  buildTypes {
    release {
      isMinifyEnabled = false
    }
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
  }
  kotlin {
    jvmToolchain(17)
  }
  publishing {
    singleVariant("release")
  }
}

afterEvaluate {
  publishing {
    publications {
      // Creates a Maven publication called "release".
      create<MavenPublication>("release") {
        // Applies the component for the release build variant.
        from(components["release"])

        // You can then customize attributes of the publication as shown below.
        groupId = libs.versions.libraryGroup.get()
        artifactId = "library"
        version = libs.versions.versionName.get()
      }
    }
  }
}

dependencies {
  implementation(libs.kotlinx.coroutines.android)
  api(project(":RootEncoder:encoder"))
  api(project(":RootEncoder:rtmp"))
  api(project(":RootEncoder:rtsp"))
  api(project(":RootEncoder:srt"))
  api(project(":RootEncoder:udp"))
  api(project(":RootEncoder:common"))
}
