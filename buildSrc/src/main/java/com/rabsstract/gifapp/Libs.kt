package com.rabsstract.gifapp

object Libs {

    object Android {
        const val TOOLS_BUILD_GRADLE = "com.android.tools.build:gradle:${Versions.Android.TOOLS_BUILD_GRADLE}"
        const val KOTLIN_GRADLE_PLUGIN = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.Android.KOTLIN_VERSION}"

        const val CORE_KTX = "androidx.core:core-ktx:${Versions.Android.CORE_KTX}"
        const val APP_COMPACT = "androidx.appcompat:appcompat:${Versions.Android.APP_COMPACT}"
        const val APP_MATERIAL =
            "com.google.android.material:material:${Versions.Android.APP_MATERIAL}"
        const val APP_CONSTRAINT =
            "androidx.constraintlayout:constraintlayout:${Versions.Android.APP_CONSTRAINT}"
        const val LIVEDATA_KTX = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.Android.LIFE_CYCLE}"
        const val VIEWMODEL_KTX =
            "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.Android.LIFE_CYCLE}"
        const val RUNTIME_KTX =
            "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.Android.LIFE_CYCLE}"

        const val PAGING =
            "androidx.paging:paging-runtime-ktx:${Versions.Android.PAGING}"
        const val SWIPE_LAYOUT =
            "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.Android.SWIPE_LAYOUT}"

    }

    object Asynchronous {
    }


    object DependencyInversion {
        const val KOIN_ANDROID = "org.koin:koin-android:${Versions.DependencyInversion.KOIN}"
        const val KOIN_SCOPE_FEATURES = "org.koin:koin-androidx-scope:${Versions.DependencyInversion.KOIN}"
        const val KOIN_VIEWMODEL_FEATURES = "org.koin:koin-androidx-viewmodel:${Versions.DependencyInversion.KOIN}"
    }

    object Testing {
        const val JUNIT = "junit:junit:${Versions.Testing.JUNIT}"
        const val JUNIT_EXT = "androidx.test.ext:junit:${Versions.Testing.JUNIT_EXT}"
        const val ESPRESSO_CORE =
            "androidx.test.espresso:espresso-core:${Versions.Testing.ESPRESSO_CORE}"
        const val TEST_RULES = "androidx.test:rules:1.3.0"
        const val MOCK_SEREVER = "com.squareup.okhttp3:mockwebserver:4.1.0"
        const val KOIN_TEST = "org.koin:koin-test:2.0.1"
        const val EXPRE_CONTI = "androidx.test.espresso:espresso-contrib:3.3.0"
        const val MOKK = "io.mockk:mockk:1.9.3"
        const val CORE_TEST = "android.arch.core:core-testing:${Versions.Testing.CORE}"
        const val PAGING_TEST =
            "androidx.paging:paging-common-ktx:${Versions.Android.PAGING}"

    }

    object UI {
        const val CARD_VIEW = "androidx.cardview:cardview:${Versions.UI.CARD_VIEW}"
        const val CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:${Versions.UI.CONSTRAINT_LAYOUT}"
        const val GLIDE = "com.github.bumptech.glide:glide:${Versions.UI.GLIDE}"
        const val GLIDE_COMPILER = "com.github.bumptech.glide:compiler:${Versions.UI.GLIDE}"
        const val RECYCLER_VIEW = "androidx.recyclerview:recyclerview:${Versions.UI.RECYCLER_VIEW}"
        const val SUPPORT_DESIGN = "com.android.support:design:${Versions.UI.SUPPORT_DESIGN}"
    }

    object KOTLIN {

    }
    object Network {
        const val RETROFIT_JSON_CONVERTER = "com.squareup.retrofit2:converter-gson:${Versions.Network.RETROFIT_JSON_CONVERTER}"
        const val RETROFIT = "com.squareup.retrofit2:${Versions.Network.RETROFIT}"
        const val RETROFIT_COROUTINES = "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Versions.Network.COROUTINES}"
        const val RETROFIT_RX_ADAPTER = "com.squareup.retrofit2:adapter-rxjava2:${Versions.Network.RETROFIT_RX_ADAPTER}"
        const val OK_HTTP = "com.squareup.okhttp3:okhttp:${Versions.Network.OK_HTTP}"
        const val OK_HTTP_LOG = "com.squareup.okhttp3:logging-interceptor:${Versions.Network.OK_HTTP}"
    }
    object Room {
        const val ROOM_RUNTIME = "androidx.room:room-runtime:${Versions.Room.ROOM}"
        const val ROOM_KTX = "androidx.room:room-ktx:${Versions.Room.ROOM}"
        const val ROOM_COMPILER = "androidx.room:room-compiler:${Versions.Room.ROOM}"
    }
}
