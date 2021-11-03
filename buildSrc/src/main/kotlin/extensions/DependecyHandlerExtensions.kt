package extensions

import dependencies.DebugDependencies
import dependencies.TestAndroidDependencies
import dependencies.TestDependencies
import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler

/**
 * Adds a dependency to the `debugImplementation` configuration.
 *
 * @param dependencyNotation name of dependency to add at specific configuration
 *
 * @return the dependency
 */
fun DependencyHandler.debugImplementation(dependencyNotation: String): Dependency? =
    add("debugImplementation", dependencyNotation)

/**
 * Adds a dependency to the `implementation` configuration.
 *
 * @param dependencyNotation name of dependency to add at specific configuration
 *
 * @return the dependency
 */
fun DependencyHandler.implementation(dependencyNotation: String): Dependency? =
    add("implementation", dependencyNotation)

/**
 * Adds a dependency to the `api` configuration.
 *
 * @param dependencyNotation name of dependency to add at specific configuration
 *
 * @return the dependency
 */
fun DependencyHandler.api(dependencyNotation: String): Dependency? =
    add("api", dependencyNotation)

/**
 * Adds a dependency to the `kapt` configuration.
 *
 * @param dependencyNotation name of dependency to add at specific configuration
 *
 * @return the dependency
 */
fun DependencyHandler.kapt(dependencyNotation: String): Dependency? =
    add("kapt", dependencyNotation)

/**
 * Adds a dependency to the `testImplementation` configuration.
 *
 * @param dependencyNotation name of dependency to add at specific configuration
 *
 * @return the dependency
 */
fun DependencyHandler.testImplementation(dependencyNotation: String): Dependency? =
    add("testImplementation", dependencyNotation)


/**
 * Adds a dependency to the `androidTestImplementation` configuration.
 *
 * @param dependencyNotation name of dependency to add at specific configuration
 *
 * @return the dependency
 */
fun DependencyHandler.androidTestImplementation(dependencyNotation: String): Dependency? =
    add("androidTestImplementation", dependencyNotation)

/**
 * Adds a dependency to the `androidTestRuntimeOnly` configuration.
 *
 * @param dependencyNotation name of dependency to add at specific configuration
 *
 * @return the dependency
 */
fun DependencyHandler.androidTestRuntimeOnly(dependencyNotation: String): Dependency? =
    add("androidTestRuntimeOnly", dependencyNotation)

/**
 * Adds all the tests dependencies to specific configuration.
 */
fun DependencyHandler.addTestsDependencies() {
    testImplementation(TestDependencies.CORE)
    testImplementation(TestDependencies.ARCH_CORE)
    testImplementation(TestDependencies.JUNIT4)
//    testImplementation(TestDependencies.JUNIT_JUPITER_API)
//    testImplementation(TestDependencies.JUNIT_JUPITER_PARAMS)
//    testImplementation(TestDependencies.JUNIT_JUPITER_ENGINE)
//    testImplementation(TestDependencies.HAMCREST)
    testImplementation(TestDependencies.KOTLIN_COROUTINES_TEST)
    testImplementation(TestDependencies.GOOGLE_TRUTH)
    testImplementation(TestDependencies.MOCKITO_CORE)
    testImplementation(TestDependencies.MOCKITO_INLINE)
    testImplementation(TestDependencies.MOCKITO_KOTLIN)
    testImplementation(TestDependencies.ROBOLECTRIC)

//    androidTestRuntimeOnly(TestAndroidDependencies.JUNIT5_ANDROID_TEST_RUNNER)

//    androidTestImplementation(TestAndroidDependencies.JUNIT5_ANDROID_TEST_CORE)
    androidTestImplementation(TestAndroidDependencies.MOCKITO_DEXMAKER)
    androidTestImplementation(TestAndroidDependencies.KOTLIN_COROUTINES_TEST)
    androidTestImplementation(TestAndroidDependencies.ARCH_CORE_TEST)
    androidTestImplementation(TestAndroidDependencies.GOOGLE_TRUTH)
    androidTestImplementation(TestAndroidDependencies.MOCKITO_CORE)
    androidTestImplementation(TestAndroidDependencies.DAGGER_HILT_TEST)
    androidTestImplementation(TestAndroidDependencies.RUNNER)
    androidTestImplementation(TestAndroidDependencies.RULES)
    androidTestImplementation(TestAndroidDependencies.FRAGMENT_TEST)
    androidTestImplementation(TestAndroidDependencies.JUNIT_KTX_EXT)

    debugImplementation(DebugDependencies.FRAGMENT_TESTING)
}

/**
 * Adds all the tests dependencies to specific configuration.
 */
fun DependencyHandler.addKotlinTestsDependencies() {
    testImplementation(TestDependencies.CORE)
    testImplementation(TestDependencies.ARCH_CORE)
    testImplementation(TestDependencies.JUNIT4)
//    testImplementation(TestDependencies.JUNIT_JUPITER_API)
//    testImplementation(TestDependencies.JUNIT_JUPITER_PARAMS)
//    testImplementation(TestDependencies.JUNIT_JUPITER_ENGINE)
    testImplementation(TestDependencies.GOOGLE_TRUTH)
    testImplementation(TestDependencies.MOCKITO_CORE)
    testImplementation(TestDependencies.MOCKITO_INLINE)
    testImplementation(TestDependencies.MOCKITO_KOTLIN)
}

fun DependencyHandler.addDaggerHiltTestsDependencies() {
    testImplementation(TestDependencies.DAGGER_HILT_ANDROID_TESTING)
    testImplementation(TestDependencies.DAGGER_HILT_ANDROID_COMPILER)
}

fun DependencyHandler.addUITestsDependencies() {
    androidTestImplementation(TestAndroidDependencies.ESPRESSO_CORE)
    androidTestImplementation(TestAndroidDependencies.ESPRESSO_INTENTS)
    androidTestImplementation(TestAndroidDependencies.ESPRESSO_CONTRIB)
    androidTestImplementation(TestAndroidDependencies.ESPRESSO_RULES)
}