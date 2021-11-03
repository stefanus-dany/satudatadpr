package dependencies

object TestDependencies {
    const val CORE = "androidx.test:core:${BuildDependenciesVersions.androidxTestCore}"
    const val ARCH_CORE = "androidx.arch.core:core-testing:${BuildDependenciesVersions.androidxTestCoreTesting}"
    const val JUNIT4 = "junit:junit:${BuildDependenciesVersions.junit4}"
    //    const val JUNIT_JUPITER_API = "org.junit.jupiter:junit-jupiter-api:${BuildDependenciesVersions.junitJupiter}"
//    const val JUNIT_JUPITER_PARAMS = "org.junit.jupiter:junit-jupiter-params:${BuildDependenciesVersions.junitJupiter}"
//    const val JUNIT_JUPITER_ENGINE = "org.junit.jupiter:junit-jupiter-engine:${BuildDependenciesVersions.junitJupiter}"
//    const val HAMCREST = "org.hamcrest:hamcrest-all:${BuildDependenciesVersions.hamcrest}"
    const val KOTLIN_COROUTINES_TEST = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${BuildDependenciesVersions.kotlinxCoroutinesTest}"
    const val GOOGLE_TRUTH = "com.google.truth:truth:${BuildDependenciesVersions.googleTruth}"
    const val MOCKITO_CORE = "org.mockito:mockito-core:${BuildDependenciesVersions.mockitoCore}"
    const val MOCKITO_INLINE = "org.mockito:mockito-inline:${BuildDependenciesVersions.mockitoInline}"
    const val MOCKITO_KOTLIN = "com.nhaarman.mockitokotlin2:mockito-kotlin:${BuildDependenciesVersions.mockitoKotlin}"
    const val ROBOLECTRIC = "org.robolectric:robolectric:${BuildDependenciesVersions.ROBOLECTRIC}"
    const val DAGGER_HILT_ANDROID_TESTING = "com.google.dagger:hilt-android-testing:${BuildDependenciesVersions.daggerHiltTesting}"
    const val DAGGER_HILT_ANDROID_COMPILER = "com.google.dagger:hilt-android-compiler:${BuildDependenciesVersions.daggerHiltTesting}"
}