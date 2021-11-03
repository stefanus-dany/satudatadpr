package dependencies

/**
 * Project test android dependencies, makes it easy to include external binaries or
 * other library modules to build.
 */
object TestAndroidDependencies {
    const val RUNNER = "androidx.test:runner:${BuildDependenciesVersions.RUNNER_TEST}"
    const val RULES = "androidx.test:rules:${BuildDependenciesVersions.RULES_TEST}"
    const val JUNIT_KTX_EXT = "androidx.test.ext:junit-ktx:${BuildDependenciesVersions.androidXJunitVersion}"
    const val FRAGMENT_TEST = "androidx.fragment:fragment-testing:${BuildDependenciesVersions.FRAGMENT_TEST}"
    const val JUNIT5_ANDROID_TEST_CORE = "de.mannodermaus.junit5:android-test-core:${BuildDependenciesVersions.junit5AndroidTest}"
    const val JUNIT5_ANDROID_TEST_RUNNER = "de.mannodermaus.junit5:android-test-runner:${BuildDependenciesVersions.junit5AndroidTest}"
    const val MOCKITO_DEXMAKER = "com.linkedin.dexmaker:dexmaker-mockito:${BuildDependenciesVersions.dexMakerMockito}"
    const val KOTLIN_COROUTINES_TEST = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${BuildDependenciesVersions.kotlinxCoroutinesTest}"
    const val ARCH_CORE_TEST = "androidx.arch.core:core-testing:${BuildDependenciesVersions.androidArchCoreTesting}"
    const val GOOGLE_TRUTH = "com.google.truth:truth:${BuildDependenciesVersions.googleTruth}"
    const val MOCKITO_CORE = "org.mockito:mockito-core:${BuildDependenciesVersions.mockitoCore}"
    const val DAGGER_HILT_TEST = "com.google.dagger:hilt-android-testing:${BuildDependenciesVersions.daggerHiltAndroidTesting}"
    const val ESPRESSO_CORE = "androidx.test.espresso:espresso-core:${BuildDependenciesVersions.espresso}"
    const val ESPRESSO_INTENTS = "androidx.test.espresso:espresso-intents:${BuildDependenciesVersions.espresso}"
    const val ESPRESSO_CONTRIB = "androidx.test.espresso:espresso-contrib:${BuildDependenciesVersions.espressoContribAndroidX}"
    const val ESPRESSO_RULES = "androidx.test:rules:${BuildDependenciesVersions.espressoRulesAndroidX}"
}