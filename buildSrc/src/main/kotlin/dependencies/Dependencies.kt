package dependencies

object Dependencies {
    const val KOTLIN = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${BuildDependenciesVersions.KOTLIN}"
    const val APPCOMPAT = "androidx.appcompat:appcompat:${BuildDependenciesVersions.appCompatX}"
    const val CORE_KTX = "androidx.core:core-ktx:${BuildDependenciesVersions.coreKtxAndroidX}"
    const val CONSTRAINT_LAYOUT  = "androidx.constraintlayout:constraintlayout:${BuildDependenciesVersions.constraintLayoutX}"
    const val COORDINATOR_LAYOUT = "androidx.coordinatorlayout:coordinatorlayout:${BuildDependenciesVersions.coordinatorLayoutX}"
    const val MATERIAL = "com.google.android.material:material:${BuildDependenciesVersions.materialGoogleAndroid}"
    const val SWIPE_REFRESH_LAYOUT = "androidx.swiperefreshlayout:swiperefreshlayout:${BuildDependenciesVersions.swipeRefreshLayout}"

    const val MULTI_DEX = "androidx.multidex:multidex:${BuildDependenciesVersions.multidex}"

    const val COROUTINES = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${BuildDependenciesVersions.kotlinxCoroutines}"
    const val COROUTINES_ANDROID = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${BuildDependenciesVersions.kotlinxCoroutines}"

    const val LIFECYCLE_VIEW_MODEL_SAVED_STATE = "androidx.lifecycle:lifecycle-viewmodel-savedstate:${BuildDependenciesVersions.lifecycleAndroidX}"
    const val LIFECYCLE_LIVEDATA = "androidx.lifecycle:lifecycle-livedata-ktx:${BuildDependenciesVersions.lifecycleAndroidX}"
    const val LIFECYCLE_RUNTIME = "androidx.lifecycle:lifecycle-runtime-ktx:${BuildDependenciesVersions.lifecycleAndroidX}"
    const val LIFECYCLE_COMMON_JAVA8 = "androidx.lifecycle:lifecycle-common-java8:${BuildDependenciesVersions.lifecycleAndroidX}"
    const val LIFECYCLE_VIEWMODEL = "androidx.lifecycle:lifecycle-viewmodel-ktx:${BuildDependenciesVersions.lifecycleAndroidX}"
    const val FRAGMENT_KTX = "androidx.fragment:fragment-ktx:${BuildDependenciesVersions.fragmentKtxAndroidX}"
    const val ACTIVITY_KTX = "androidx.activity:activity-ktx:${BuildDependenciesVersions.activityKtxAndroidX}"

    const val NAVIGATION_FRAGMENT = "androidx.navigation:navigation-fragment-ktx:${BuildDependenciesVersions.navigationKtxAndroidX}"
    const val NAVIGATION_UI = "androidx.navigation:navigation-ui-ktx:${BuildDependenciesVersions.navigationKtxAndroidX}"
    const val NAVIGATION_DYNAMIC_FEATURE_FRAGMENT = "androidx.navigation:navigation-dynamic-features-fragment:${BuildDependenciesVersions.navigationKtxAndroidX}"

    const val GSON = "com.google.code.gson:gson:${BuildDependenciesVersions.gson}"
    const val RETROFIT = "com.squareup.retrofit2:retrofit:${BuildDependenciesVersions.retrofit}"
    const val RETROFIT_CONVERTER_GSON = "com.squareup.retrofit2:converter-gson:${BuildDependenciesVersions.retrofit}"
    const val RETROFIT_CONVERTER_SCALARS = "com.squareup.retrofit2:converter-scalars:${BuildDependenciesVersions.retrofit}"
    const val OKHTTP3 = "com.squareup.okhttp3:okhttp:${BuildDependenciesVersions.okHttp3}"
    const val OKHTTP3_LOGGING_INTERCEPTOR = "com.squareup.okhttp3:logging-interceptor:${BuildDependenciesVersions.okHttp3}"
    const val NAVIGATION_RUNTIME = "androidx.navigation:navigation-runtime-ktx:${BuildDependenciesVersions.navigationKtxAndroidX}"

    const val DAGGER = "com.google.dagger:dagger:${BuildDependenciesVersions.dagger}"
    const val DAGGER_ANDROID = "com.google.dagger:dagger-android:${BuildDependenciesVersions.dagger}"
    const val DAGGER_ANDROID_SUPPORT = "com.google.dagger:dagger-android-support:${BuildDependenciesVersions.dagger}"
    const val LIFECYCLE_VIEWMODEL_HILT = "androidx.hilt:hilt-lifecycle-viewmodel:${BuildDependenciesVersions.hiltAndroidX}"
    const val NAVIGATION_FRAGMENT_HILT = "androidx.hilt:hilt-navigation-fragment:${BuildDependenciesVersions.hiltAndroidX}"
    const val COMPILER_HILT = "androidx.hilt:hilt-compiler:${BuildDependenciesVersions.hiltAndroidX}"
    const val DAGGER_HILT_ANDROID = "com.google.dagger:hilt-android:${BuildDependenciesVersions.daggerHiltAndroid}"
    const val COMMON_HILT = "androidx.hilt:hilt-common:${BuildDependenciesVersions.hiltAndroidX}"

    const val TIMBER = "com.jakewharton.timber:timber:${BuildDependenciesVersions.timber}"

    const val PREFERENCE_KTX = "androidx.preference:preference-ktx:${BuildDependenciesVersions.preferenceKotlinAndroidX}"

    const val CAMERAX_CORE = "androidx.camera:camera-core:${BuildDependenciesVersions.cameraAndroidX}"
    const val CAMERA2 = "androidx.camera:camera-camera2:${BuildDependenciesVersions.cameraAndroidX}"
    const val CAMERA_LIFECYCLE = "androidx.camera:camera-lifecycle:${BuildDependenciesVersions.cameraAndroidX}"
    const val CAMERA_VIEW = "androidx.camera:camera-view:${BuildDependenciesVersions.cameraView}"
    const val CAMERA_EXTENSIONS = "androidx.camera:camera-extensions:${BuildDependenciesVersions.cameraExtensions}"

    const val GLIDE = "com.github.bumptech.glide:glide:${BuildDependenciesVersions.glide}"
    const val ROOM = "androidx.room:room-ktx:${BuildDependenciesVersions.roomKtx}"
    const val ROOM_RUNTIME = "androidx.room:room-runtime:${BuildDependenciesVersions.roomKtx}"
    const val GRID_LAYOUT = "com.android.support:gridlayout-v7:${BuildDependenciesVersions.gridLayout}"
    const val RECYCLE_VIEW = "com.android.support:recyclerview-v7:${BuildDependenciesVersions.recyclerview}"

    const val FIREBASE_BOM = "com.google.firebase:firebase-bom:${BuildDependenciesVersions.firebase}"
    const val FIREBASE_CRASHLYTICS = "com.google.firebase:firebase-crashlytics-ktx"
    const val FIREBASE_ANALYTICS = "com.google.firebase:firebase-analytics-ktx"
    const val GOOGLE_ML_KIT_BARCODE_SCANNING = "com.google.mlkit:barcode-scanning:${BuildDependenciesVersions.mlkitBarcodeScanning}"

    const val EASYPERMISSIONS = "pub.devrel:easypermissions:${BuildDependenciesVersions.easyPermissions}"
    //    const val GUAVA_GOOGLE = "com.google.guava:listenablefuture:9999.0-empty-to-avoid-conflict-with-guava"
    const val GUAVA_GOOGLE = "com.google.guava:guava:27.1-jre"
    const val KOTLIN_REFLECT = "org.jetbrains.kotlin:kotlin-reflect:${BuildDependenciesVersions.KOTLIN}"

    const val BIOMETRIC = "androidx.biometric:biometric:${BuildDependenciesVersions.biometric}"

    const val WORK_RUNTIME = "androidx.work:work-runtime-ktx:${BuildDependenciesVersions.workRuntime}"
    const val WORK_HILT = "androidx.hilt:hilt-work:${BuildDependenciesVersions.hiltAndroidX}"
    const val SERVICES_LOCATION = "com.google.android.gms:play-services-location:${BuildDependenciesVersions.serviceLocation}"
    const val EXIFINTERFACE = "androidx.exifinterface:exifinterface:${BuildDependenciesVersions.exifinterface}"
    const val CIRCLE_IMAGE_VIEW = "de.hdodenhof:circleimageview:${BuildDependenciesVersions.circleImageView}"
    const val MP_ANDROID_CHART = "com.github.PhilJay:MPAndroidChart:${BuildDependenciesVersions.mpAndroidChart}"
    const val ANY_CHART_ANDROID = "com.github.AnyChart:AnyChart-Android:${BuildDependenciesVersions.anyChartAndroid}"

    const val IMAGE_SLIDE_SHOW = "com.github.denzcoskun:ImageSlideshow:${BuildDependenciesVersions.imageSlideShow}"
    const val VIEW_PAGER2 = "androidx.viewpager2:viewpager2:${BuildDependenciesVersions.viewPager2}"

    const val TABLE_VIEW = "com.github.ISchwarz23:SortableTableView:${BuildDependenciesVersions.tableView}"

    //first letter big size for news
    const val DROP_CAP = "com.novoda:drop-cap:${BuildDependenciesVersions.DROP_CAP}"

    //heatmap google map
    const val ANDROID_MAPS_UTILS = "com.google.maps.android:android-maps-utils:${BuildDependenciesVersions.ANDROID_MAPS_UTILS}"
    const val PLAY_SERVICE_MAPS = "com.google.android.gms:play-services-maps:${BuildDependenciesVersions.PLAY_SERVICE_MAPS}"

}