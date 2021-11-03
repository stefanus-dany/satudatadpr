plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
}
object PluginsVersions {
    const val GRADLE_ANDROID = "4.2.2"
    const val KOTLIN = "1.5.20"
    const val NAVIGATION = "2.3.2"
    const val DAGGER_HILT = "2.37"
}

dependencies {
    implementation("com.android.tools.build:gradle:${PluginsVersions.GRADLE_ANDROID}")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:${PluginsVersions.KOTLIN}")
    implementation("org.jetbrains.kotlin:kotlin-allopen:1.4.32")
    implementation("androidx.navigation:navigation-safe-args-gradle-plugin:${PluginsVersions.NAVIGATION}")
    implementation("com.google.dagger:hilt-android-gradle-plugin:${PluginsVersions.DAGGER_HILT}")
    implementation(kotlin("gradle-plugin", PluginsVersions.KOTLIN))
}